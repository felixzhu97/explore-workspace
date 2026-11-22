process.env.TZ = 'Asia/Shanghai';

jest.mock('@react-native-async-storage/async-storage', () =>
  require('@react-native-async-storage/async-storage/jest/async-storage-mock'),
);

jest.mock(
  'expo-modules-core',
  () => ({
    requireNativeModule: () => ({}),
    NativeModulesProxy: {},
    EventEmitter: class {
      addListener() {
        return { remove() {} };
      }
    },
  }),
  { virtual: true },
);

jest.mock(
  'expo-asset',
  () => ({
    Asset: {
      fromModule: () => ({ downloadAsync: jest.fn(), uri: '' }),
      loadAsync: jest.fn(),
    },
  }),
  { virtual: true },
);

jest.mock(
  'expo-font',
  () => ({
    loadAsync: jest.fn(),
    isLoaded: () => true,
    processFontFamily: (name: string) => name,
  }),
  { virtual: true },
);

jest.mock(
  'expo-blur',
  () => {
    const React = require('react');
    const { View } = require('react-native');
    return {
      BlurView: ({ children, ...props }: { children?: React.ReactNode }) =>
        React.createElement(View, props, children),
    };
  },
  { virtual: true },
);

jest.mock(
  'expo-localization',
  () => ({
    getLocales: () => [{ languageCode: 'en', regionCode: 'US' }],
    locale: 'en-US',
  }),
  { virtual: true },
);

jest.mock(
  '@expo/vector-icons',
  () => {
    const React = require('react');
    const { Text } = require('react-native');
    const Icon = (props: Record<string, unknown>) =>
      React.createElement(Text, props, (props.name as string) ?? 'icon');
    return new Proxy(
      {},
      {
        get: () => Icon,
      },
    );
  },
  { virtual: true },
);

jest.mock('react-i18next', () => ({
  useTranslation: () => ({
    t: (key: string, options?: { count?: number }) => {
      const translations: Record<string, string> = {
        'chats.activeNow': 'Active now',
        'chats.yesterday': 'Yesterday',
        'chats.sentDaysAgo': '{count} days ago',
        'chats.sentLastWeek': 'Last week',
        'chats.sentLastMonth': 'Last month',
        'feed.likeLabel': 'likes',
        'feed.follow': 'Follow',
        'feed.unfollow': 'Unfollow',
        'feed.copyLink': 'Copy Link',
        'feed.report': 'Report',
        'common.cancel': 'Cancel',
      };
      const template = translations[key] || key;
      return options?.count
        ? template.replace('{count}', String(options.count))
        : template;
    },
  }),
  initReactI18next: {
    type: '3rdParty',
    init: jest.fn(),
  },
}));
