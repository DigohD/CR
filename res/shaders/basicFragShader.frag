#version 130

in vec2 texCoord;
in vec3 outColor;

uniform sampler2D sampler;
uniform sampler2D envMap;

void main() 
{
	vec4 t0 = texture2D(envMap, texCoord.xy);
	vec4 t1 = texture2D(sampler, texCoord.xy);
	gl_FragColor =  t0 + t1 * vec4(outColor, 1.0);
}