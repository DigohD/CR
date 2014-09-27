#version 130

in vec2 texCoord;
in vec3 outColor;

uniform sampler2D sampler;
uniform sampler2D envMap;

void main() 
{
	gl_FragColor = texture2D(envMap, texCoord.xy)  * vec4(outColor, 1.0);
}