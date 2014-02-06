#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
varying float v_time;

uniform sampler2D u_texture;

uniform float frequency;
uniform float noiseFactor;
uniform float time;
uniform float intensity;
uniform float lineSpeed;

float rand(vec2 co, float time){
    return fract(sin(dot(co.xy ,vec2(12.9898 + time / 10.0,78.233))) * 43758.5453);
}

void main() {
	float speedFactor = 1.0 / lineSpeed * 10.0;
	float global_pos = (v_texCoords.y + time / speedFactor + 1000.0) * frequency;
    float wave_pos = cos((fract( global_pos ) - 0.5)*intensity);
    vec4 pel = texture2D( u_texture, v_texCoords );
    
    vec4 colorNoise = mix(pel, pel * rand(v_texCoords, time),noiseFactor);
    vec4 color = mix(colorNoise / 1.5, colorNoise, wave_pos + 0.5);
    gl_FragColor = color;
}