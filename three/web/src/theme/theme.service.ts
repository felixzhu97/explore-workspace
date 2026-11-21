import { Injectable } from '@angular/core';

/** Product theme id — aligns with `prefers-color-scheme` light/dark naming. */
export type ThemeId = 'light' | 'dark';

/** RGB color in product space (0..1). */
export interface RouteColor {
  readonly r: number;
  readonly g: number;
  readonly b: number;
}

/** Product appearance — no shaders / Three types. */
export interface ThemeAppearance {
  readonly type: ThemeId;
  readonly lightConfig: {
    readonly pointColor: number;
    readonly pointIntensity: number;
    readonly hemisphereColor: number;
    readonly hemisphereIntensity: number;
  };
  readonly buttonStroke: string;
  readonly sourceUrl: string;
}

const REPO_URL = 'https://github.com/felixzhu97/explore-three';

const LIGHT_THEME: ThemeAppearance = {
  type: 'light',
  lightConfig: {
    pointColor: 0xffffff,
    pointIntensity: 3,
    hemisphereColor: 0xffffff,
    hemisphereIntensity: 1.5,
  },
  buttonStroke: '#333333',
  sourceUrl: REPO_URL,
};

const DARK_THEME: ThemeAppearance = {
  type: 'dark',
  lightConfig: {
    pointColor: 0x081b26,
    pointIntensity: 17,
    hemisphereColor: 0xffffff,
    hemisphereIntensity: 1.5,
  },
  buttonStroke: '#ffffff',
  sourceUrl: REPO_URL,
};

const LIGHT_COLORS: readonly RouteColor[] = [
  { r: 0.3, g: 0.8, b: 1.0 },
  { r: 1.0, g: 0.5, b: 0.3 },
  { r: 0.5, g: 1.0, b: 0.3 },
  { r: 1.0, g: 0.3, b: 0.8 },
  { r: 0.8, g: 0.8, b: 0.3 },
  { r: 0.6, g: 0.3, b: 1.0 },
];

const DARK_COLORS: readonly RouteColor[] = [
  { r: 0.0, g: 0.8, b: 1.0 },
  { r: 0.0, g: 0.4, b: 0.8 },
  { r: 0.2, g: 1.0, b: 0.8 },
  { r: 0.3, g: 0.6, b: 1.0 },
  { r: 0.0, g: 0.9, b: 0.6 },
  { r: 0.1, g: 0.5, b: 0.9 },
];

@Injectable({ providedIn: 'root' })
export class ThemeService {
  readonly lightTheme = LIGHT_THEME;
  readonly darkTheme = DARK_THEME;
  readonly lightColors = LIGHT_COLORS;
  readonly darkColors = DARK_COLORS;

  appearance(id: ThemeId): ThemeAppearance {
    return id === 'light' ? LIGHT_THEME : DARK_THEME;
  }

  colors(id: ThemeId): readonly RouteColor[] {
    return id === 'light' ? LIGHT_COLORS : DARK_COLORS;
  }
}