#version 130

in vec2 texCoord;
in vec3 outColor;

uniform sampler2D tex0;
uniform sampler2D tex1;
uniform sampler2D mask;

void main() 
{
	vec4 texColor0 = texture2D(tex0, texCoord.xy);
	vec4 texColor1 = texture2D(tex1, texCoord.xy);
	float mask = texture2D(mask, texCoord.xy).a;
	
	gl_FragColor = vec4(outColor, 1.0) * mix(texColor0, texColor1, mask);
}