const { spawn } = require('node:child_process');
const { connect } = require('node:net');

const [service, port] = process.argv.slice(2);

/** Reuses a service already listening on the host port, e.g. a native Ollama with GPU access. */
function isListening(portNumber) {
  return new Promise((resolve) => {
    const socket = connect({ host: '127.0.0.1', port: Number(portNumber) });
    socket.once('connect', () => socket.end(() => resolve(true)));
    socket.once('error', () => resolve(false));
  });
}

async function main() {
  if (port && (await isListening(port))) {
    console.log(`${service} is already running on port ${port}; reusing it.`);
    setInterval(() => {}, 1 << 30);
    return;
  }
  const child = spawn('docker', ['compose', 'up', service], { stdio: 'inherit' });
  for (const signal of ['SIGINT', 'SIGTERM']) {
    process.on(signal, () => child.kill(signal));
  }
  child.on('exit', (code) => process.exit(code ?? 0));
}

main();
