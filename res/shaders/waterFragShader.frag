#version 130

in vec2 texCoord;
in vec3 outColor;

uniform sampler2D sampler;
uniform sampler2D sampler2;

void main() 
{
	vec2 a2DVectorTemp = vec2(0, 0.26f);
	vec2 a2DVector = texCoord + a2DVectorTemp;
	
	gl_FragColor =( texture2D(sampler, texCoord.xy) + (texture2D(sampler2, a2DVector.xy) * 0.3f) ) * vec4(outColor, 1);
}