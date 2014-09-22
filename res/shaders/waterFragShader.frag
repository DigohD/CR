#version 130

in vec2 texCoord;
in vec3 outColor;

uniform sampler2D sampler;
uniform sampler2D sampler2;

void main() 
{
	gl_FragColor = texture2D(sampler, texCoord.xy) * vec4(outColor, 1);
}