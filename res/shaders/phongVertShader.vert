#version 130

in vec3 position;
in vec2 texCoordIn;
in vec3 normalIn;
in vec3 tangentIn;

out vec3 viewSpaceNormal;
out vec3 viewSpaceTangent;
out vec3 viewSpacePos;
out vec2 texCoord; 
out float isWater_out;
out mat4 modelview;

uniform float isWater;

uniform float waveDataX;
uniform float waveDataY;


uniform mat4 transformation;
uniform mat4 modelViewMatrix;

void main() 
{
	modelview = modelViewMatrix;
	isWater_out = isWater;
	texCoord = texCoordIn;
	viewSpacePos = (modelViewMatrix * vec4(position, 1.0)).xyz;
	viewSpaceNormal = (modelViewMatrix * vec4(normalIn, 0.0)).xyz;
	viewSpaceTangent = (modelViewMatrix * vec4(tangentIn, 0.0)).xyz;
	
	if(isWater == 1.0){
		vec4 a_position = vec4(position, 1);
	
		float x = a_position.x + waveDataY * sin(waveDataX + a_position.x + a_position.y);
		float y = a_position.y + waveDataY * cos(waveDataX + a_position.x + a_position.y);
	
		vec4 newPos = vec4(x, y, a_position.z, a_position.w);
		
		gl_Position = transformation * newPos;
	}else{
		gl_Position = transformation * vec4(position, 1);
	}
}