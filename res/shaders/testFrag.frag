#version 130

in vec2 texCoord;
in vec3 outColor;

uniform vec3 res;

uniform sampler2D tex0;
uniform sampler2D lightMap;
uniform sampler2D mask;

void main() 
{

	//vec2 lightCoord = (gl_FragCoord.xy / res.xy);
	//vec4 light = texture2D(lightMap, lightCoord);
	vec4 texColor0 = texture2D(tex0, texCoord.xy);
	//vec4 texColor1 = texture2D(tex1, texCoord.xy);
	//float mask = texture2D(mask, texCoord.xy).a;
	
	gl_FragColor = vec4(outColor, 1.0)  *  texColor0;
	
	//gl_FragColor = vec4(outColor, 1.0) * mix(texColor0, texColor1, mask);
}