#version 130

in vec2 texCoord;
in vec3 outColor;

uniform sampler2D sampler;
uniform sampler2D sampler1;
uniform sampler2D sampler2;

void main() 
{
	vec4 t1 = texture2D(sampler1, texCoord.xy);
	vec4 t2 = texture2D(sampler2, texCoord.xy);

	gl_FragColor =  (t1 * 0.5) + (t2 * (1.0 - 0.5)) * vec4(outColor, 1.0);
	//gl_FragColor = texture2D(sampler, texCoord.xy) * 1.2 * vec4(outColor, 1.0);
}