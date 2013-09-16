uniform sampler2D bgl_RenderedTexture;

uniform int steps = 5;
uniform float radius = 0.0010f;

void main()
{
	vec4 sum = vec4(0);
	vec2 texcoord = vec2(gl_TexCoord[0]).st;
	int j;
	int i;
	
	for( i= -steps ;i < steps; i++)
	{
		for (j = -steps; j < steps; j++)
		{
			sum += texture2D(bgl_RenderedTexture, texcoord + vec2(j, i)*radius) * 0.20; 
		}
	}
	
	gl_FragColor = sum*sum*0.006+texture2D(bgl_RenderedTexture, texcoord);
}