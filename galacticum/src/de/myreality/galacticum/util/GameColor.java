/* Galacticum space game for Android, PC and browser.
 * Copyright (C) 2013  Miguel Gonzalez
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.myreality.galacticum.util;

import java.io.Serializable;

import com.badlogic.gdx.utils.NumberUtils;

/**
 * Custom GameColor implementation, because LibGDX sucks
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class GameColor implements Serializable {

		private static final long serialVersionUID = 1L;
		public static final GameColor CLEAR = new GameColor(0, 0, 0, 0);
        public static final GameColor WHITE = new GameColor(1, 1, 1, 1);
        public static final GameColor BLACK = new GameColor(0, 0, 0, 1);
        public static final GameColor RED = new GameColor(1, 0, 0, 1);
        public static final GameColor GREEN = new GameColor(0, 1, 0, 1);
        public static final GameColor BLUE = new GameColor(0, 0, 1, 1);
        public static final GameColor LIGHT_GRAY = new GameColor(0.75f, 0.75f, 0.75f, 1);
        public static final GameColor GRAY = new GameColor(0.5f, 0.5f, 0.5f, 1);
        public static final GameColor DARK_GRAY = new GameColor(0.25f, 0.25f, 0.25f, 1);
        public static final GameColor PINK = new GameColor(1, 0.68f, 0.68f, 1);
        public static final GameColor ORANGE = new GameColor(1, 0.78f, 0, 1);
        public static final GameColor YELLOW = new GameColor(1, 1, 0, 1);
        public static final GameColor MAGENTA = new GameColor(1, 0, 1, 1);
        public static final GameColor CYAN = new GameColor(0, 1, 1, 1);

        @Deprecated public static GameColor tmp = new GameColor();

        /** the red, green, blue and alpha components **/
        public float r, g, b, a;

        /** Constructs a new GameColor with all components set to 0. */
        public GameColor () {
        }

        /** @see #rgba8888ToGameColor(GameColor, int) */
        public GameColor (int rgba8888) {
                rgba8888ToGameColor(this, rgba8888);
        }

        /** Constructor, sets the components of the GameColor
         * 
         * @param r the red component
         * @param g the green component
         * @param b the blue component
         * @param a the alpha component */
        public GameColor (float r, float g, float b, float a) {
                this.r = r;
                this.g = g;
                this.b = b;
                this.a = a;
                clamp();
        }

        /** Constructs a new GameColor using the given GameColor
         * 
         * @param GameColor the GameColor */
        public GameColor (GameColor GameColor) {
                set(GameColor);
        }

        /** Sets this GameColor to the given GameColor.
         * 
         * @param GameColor the GameColor */
        public GameColor set (GameColor GameColor) {
                this.r = GameColor.r;
                this.g = GameColor.g;
                this.b = GameColor.b;
                this.a = GameColor.a;
                return this;
        }

        /** Multiplies the this GameColor and the given GameColor
         * 
         * @param GameColor the GameColor
         * @return this GameColor. */
        public GameColor mul (GameColor GameColor) {
                this.r *= GameColor.r;
                this.g *= GameColor.g;
                this.b *= GameColor.b;
                this.a *= GameColor.a;
                return clamp();
        }

        /** Multiplies all components of this GameColor with the given value.
         * 
         * @param value the value
         * @return this GameColor */
        public GameColor mul (float value) {
                this.r *= value;
                this.g *= value;
                this.b *= value;
                this.a *= value;
                return clamp();
        }

        /** Adds the given GameColor to this GameColor.
         * 
         * @param GameColor the GameColor
         * @return this GameColor */
        public GameColor add (GameColor GameColor) {
                this.r += GameColor.r;
                this.g += GameColor.g;
                this.b += GameColor.b;
                this.a += GameColor.a;
                return clamp();
        }

        /** Subtracts the given GameColor from this GameColor
         * 
         * @param GameColor the GameColor
         * @return this GameColor */
        public GameColor sub (GameColor GameColor) {
                this.r -= GameColor.r;
                this.g -= GameColor.g;
                this.b -= GameColor.b;
                this.a -= GameColor.a;
                return clamp();
        }

        /** @return this GameColor for chaining */
        public GameColor clamp () {
                if (r < 0)
                        r = 0;
                else if (r > 1) r = 1;

                if (g < 0)
                        g = 0;
                else if (g > 1) g = 1;

                if (b < 0)
                        b = 0;
                else if (b > 1) b = 1;

                if (a < 0)
                        a = 0;
                else if (a > 1) a = 1;
                return this;
        }

        /** @return this GameColor for chaining */
        public GameColor set (float r, float g, float b, float a) {
                this.r = r;
                this.g = g;
                this.b = b;
                this.a = a;
                return clamp();
        }

        /** @return this GameColor for chaining
         * @see #rgba8888ToGameColor(GameColor, int) */
        public GameColor set (int rgba) {
                rgba8888ToGameColor(this, rgba);
                return this;
        }

        /** @return this GameColor for chaining */
        public GameColor add (float r, float g, float b, float a) {
                this.r += r;
                this.g += g;
                this.b += b;
                this.a += a;
                return clamp();
        }

        /** @return this GameColor for chaining */
        public GameColor sub (float r, float g, float b, float a) {
                this.r -= r;
                this.g -= g;
                this.b -= b;
                this.a -= a;
                return clamp();
        }

        /** @return this GameColor for chaining */
        public GameColor mul (float r, float g, float b, float a) {
                this.r *= r;
                this.g *= g;
                this.b *= b;
                this.a *= a;
                return clamp();
        }

        /** Linearly interpolates between this GameColor and the target GameColor by t which is in the range [0,1]. The result is stored in this
         * GameColor.
         * @param target The target GameColor
         * @param t The interpolation coefficient
         * @return This GameColor for chaining. */
        public GameColor lerp (final GameColor target, final float t) {
                this.r += t * (target.r - this.r);
                this.g += t * (target.g - this.g);
                this.b += t * (target.b - this.b);
                this.a += t * (target.a - this.a);
                return clamp();
        }

        /** Linearly interpolates between this GameColor and the target GameColor by t which is in the range [0,1]. The result is stored in this
         * GameColor.
         * @param r The red component of the target GameColor
         * @param g The green component of the target GameColor
         * @param b The blue component of the target GameColor
         * @param a The alpha component of the target GameColor
         * @param t The interpolation coefficient
         * @return This GameColor for chaining. */
        public GameColor lerp (final float r, final float g, final float b, final float a, final float t) {
                this.r += t * (r - this.r);
                this.g += t * (g - this.g);
                this.b += t * (b - this.b);
                this.a += t * (a - this.a);
                return clamp();
        }

        /** Multiplies the RGB values by the alpha. */
        public GameColor premultiplyAlpha () {
                r *= a;
                g *= a;
                b *= a;
                return this;
        }

        @Override
        public boolean equals (Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                GameColor GameColor = (GameColor)o;
                return toIntBits() == GameColor.toIntBits();
        }

        @Override
        public int hashCode () {
                int result = (r != +0.0f ? NumberUtils.floatToIntBits(r) : 0);
                result = 31 * result + (g != +0.0f ? NumberUtils.floatToIntBits(g) : 0);
                result = 31 * result + (b != +0.0f ? NumberUtils.floatToIntBits(b) : 0);
                result = 31 * result + (a != +0.0f ? NumberUtils.floatToIntBits(a) : 0);
                return result;
        }

        /** Packs the GameColor components into a 32-bit integer with the format ABGR and then converts it to a float.
         * @return the packed GameColor as a 32-bit float
         * @see NumberUtils#intToFloatGameColor(int) */
        public float toFloatBits () {
                int GameColor = ((int)(255 * a) << 24) | ((int)(255 * b) << 16) | ((int)(255 * g) << 8) | ((int)(255 * r));
                return NumberUtils.intToFloatColor(GameColor);
        }

        /** Packs the GameColor components into a 32-bit integer with the format ABGR.
         * @return the packed GameColor as a 32-bit int. */
        public int toIntBits () {
                int GameColor = ((int)(255 * a) << 24) | ((int)(255 * b) << 16) | ((int)(255 * g) << 8) | ((int)(255 * r));
                return GameColor;
        }

        /** Returns the GameColor encoded as hex string with the format RRGGBBAA. */
        @Override
        public String toString () {
                String value = Integer.toHexString(((int)(255 * r) << 24) | ((int)(255 * g) << 16) | ((int)(255 * b) << 8)
                        | ((int)(255 * a)));
                while (value.length() < 8)
                        value = "0" + value;
                return value;
        }

        /** Returns a new GameColor from a hex string with the format RRGGBBAA.
         * @see #toString() */
        public static GameColor valueOf (String hex) {
                int r = Integer.valueOf(hex.substring(0, 2), 16);
                int g = Integer.valueOf(hex.substring(2, 4), 16);
                int b = Integer.valueOf(hex.substring(4, 6), 16);
                int a = hex.length() != 8 ? 255 : Integer.valueOf(hex.substring(6, 8), 16);
                return new GameColor(r / 255f, g / 255f, b / 255f, a / 255f);
        }

        /** Packs the GameColor components into a 32-bit integer with the format ABGR and then converts it to a float. Note that no range
         * checking is performed for higher performance.
         * @param r the red component, 0 - 255
         * @param g the green component, 0 - 255
         * @param b the blue component, 0 - 255
         * @param a the alpha component, 0 - 255
         * @return the packed GameColor as a float
         * @see NumberUtils#intToFloatGameColor(int) */
        public static float toFloatBits (int r, int g, int b, int a) {
                int GameColor = (a << 24) | (b << 16) | (g << 8) | r;
                float floatGameColor = NumberUtils.intToFloatColor(GameColor);
                return floatGameColor;
        }

        /** Packs the GameColor components into a 32-bit integer with the format ABGR and then converts it to a float.
         * @return the packed GameColor as a 32-bit float
         * @see NumberUtils#intToFloatGameColor(int) */
        public static float toFloatBits (float r, float g, float b, float a) {
                int GameColor = ((int)(255 * a) << 24) | ((int)(255 * b) << 16) | ((int)(255 * g) << 8) | ((int)(255 * r));
                return NumberUtils.intToFloatColor(GameColor);
        }

        /** Packs the GameColor components into a 32-bit integer with the format ABGR. Note that no range checking is performed for higher
         * performance.
         * @param r the red component, 0 - 255
         * @param g the green component, 0 - 255
         * @param b the blue component, 0 - 255
         * @param a the alpha component, 0 - 255
         * @return the packed GameColor as a 32-bit int */
        public static int toIntBits (int r, int g, int b, int a) {
                return (a << 24) | (b << 16) | (g << 8) | r;
        }

        public static int alpha (float alpha) {
                return (int)(alpha * 255.0f);
        }

        public static int luminanceAlpha (float luminance, float alpha) {
                return ((int)(luminance * 255.0f) << 8) | (int)(alpha * 255);
        }

        public static int rgb565 (float r, float g, float b) {
                return ((int)(r * 31) << 11) | ((int)(g * 63) << 5) | (int)(b * 31);
        }

        public static int rgba4444 (float r, float g, float b, float a) {
                return ((int)(r * 15) << 12) | ((int)(g * 15) << 8) | ((int)(b * 15) << 4) | (int)(a * 15);
        }

        public static int rgb888 (float r, float g, float b) {
                return ((int)(r * 255) << 16) | ((int)(g * 255) << 8) | (int)(b * 255);
        }

        public static int rgba8888 (float r, float g, float b, float a) {
                return ((int)(r * 255) << 24) | ((int)(g * 255) << 16) | ((int)(b * 255) << 8) | (int)(a * 255);
        }

        public static int rgb565 (GameColor GameColor) {
                return ((int)(GameColor.r * 31) << 11) | ((int)(GameColor.g * 63) << 5) | (int)(GameColor.b * 31);
        }

        public static int rgba4444 (GameColor GameColor) {
                return ((int)(GameColor.r * 15) << 12) | ((int)(GameColor.g * 15) << 8) | ((int)(GameColor.b * 15) << 4) | (int)(GameColor.a * 15);
        }

        public static int rgb888 (GameColor GameColor) {
                return ((int)(GameColor.r * 255) << 16) | ((int)(GameColor.g * 255) << 8) | (int)(GameColor.b * 255);
        }

        public static int rgba8888 (GameColor GameColor) {
                return ((int)(GameColor.r * 255) << 24) | ((int)(GameColor.g * 255) << 16) | ((int)(GameColor.b * 255) << 8) | (int)(GameColor.a * 255);
        }

        /** Sets the GameColor components using the specified integer value in the format RGB565. This is inverse to the rgb565(r, g, b)
         * method.
         * 
         * @param GameColor The GameColor to be modified.
         * @param value An integer GameColor value in RGB565 format. */
        public static void rgb565ToGameColor (GameColor GameColor, int value) {
                GameColor.r = ((value & 0x0000F800) >>> 11) / 31f;
                GameColor.g = ((value & 0x000007E0) >>> 5) / 63f;
                GameColor.b = ((value & 0x0000001F) >>> 0) / 31f;
        }

        /** Sets the GameColor components using the specified integer value in the format RGBA4444. This is inverse to the rgba4444(r, g, b,
         * a) method.
         * 
         * @param GameColor The GameColor to be modified.
         * @param value An integer GameColor value in RGBA4444 format. */
        public static void rgba4444ToGameColor (GameColor GameColor, int value) {
                GameColor.r = ((value & 0x0000f000) >>> 12) / 15f;
                GameColor.g = ((value & 0x00000f00) >>> 8) / 15f;
                GameColor.b = ((value & 0x000000f0) >>> 4) / 15f;
                GameColor.a = ((value & 0x0000000f)) / 15f;
        }

        /** Sets the GameColor components using the specified integer value in the format RGB888. This is inverse to the rgb888(r, g, b)
         * method.
         * 
         * @param GameColor The GameColor to be modified.
         * @param value An integer GameColor value in RGB888 format. */
        public static void rgb888ToGameColor (GameColor GameColor, int value) {
                GameColor.r = ((value & 0x00ff0000) >>> 16) / 255f;
                GameColor.g = ((value & 0x0000ff00) >>> 8) / 255f;
                GameColor.b = ((value & 0x000000ff)) / 255f;
        }

        /** Sets the GameColor components using the specified integer value in the format RGBA8888. This is inverse to the rgba8888(r, g, b,
         * a) method.
         * 
         * @param GameColor The GameColor to be modified.
         * @param value An integer GameColor value in RGBA8888 format. */
        public static void rgba8888ToGameColor (GameColor GameColor, int value) {
                GameColor.r = ((value & 0xff000000) >>> 24) / 255f;
                GameColor.g = ((value & 0x00ff0000) >>> 16) / 255f;
                GameColor.b = ((value & 0x0000ff00) >>> 8) / 255f;
                GameColor.a = ((value & 0x000000ff)) / 255f;
        }

        /** Returns a temporary copy of this GameColor. This is not thread safe, do not save a reference to this instance.
         * 
         * @return a temporary copy of this GameColor */
        public GameColor tmp () {
                return tmp.set(this);
        }

        /** @return a copy of this GameColor */
        public GameColor cpy () {
                return new GameColor(this);
        }

}
