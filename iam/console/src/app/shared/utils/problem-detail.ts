/**
 * Returns the client-safe message of an API error body.
 *
 * Reads the RFC 9457 `detail` first, then the legacy `message` that servers
 * deployed before the problem-detail change still return.
 */
export function readErrorDetail(body: unknown): string | null {
  if (body === null || typeof body !== 'object') {
    return null;
  }
  const { detail, message } = body as Record<string, unknown>;
  return [detail, message]
    .find((value): value is string => typeof value === 'string' && value.length > 0)
    ?? null;
}
