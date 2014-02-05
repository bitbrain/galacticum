attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform float u_time;
uniform mat4 u_projTrans;

varying vec4 v_color;
varying vec2 v_texCoords;
varying float v_time;

void main() {
    v_color = a_color;
    v_texCoords = a_texCoord0;
    v_time = u_time;
    gl_Position = u_projTrans * a_position;
}