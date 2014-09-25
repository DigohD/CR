#version 130

in vec3 position;
in vec2 texCoordIn;
in vec3 normalIn;

out vec3 viewSpaceNormal;
out vec2 texCoord; 
out float isWater_out;

uniform float isWater;

uniform float waveDataX;
uniform float waveDataY;
uniform float time;

uniform mat4 transformation;
uniform mat4 modelViewMatrix;

void main() 
{
	isWater_out = isWater;
	texCoord = texCoordIn;
	viewSpaceNormal = (modelViewMatrix * vec4(normalIn, 0.0)).xyz;
	
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