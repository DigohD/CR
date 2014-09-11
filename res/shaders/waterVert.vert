#version 130

in vec3 position;
in vec2 texCoordIn;


out vec2 texCoord; 
out vec3 outColor;

uniform mat4 transformation;
uniform vec2 waveData;


void main() 
{
	texCoord = texCoordIn;
	vec4 newPos = vec4(position.x + waveData.y * sin(waveData.x + position.x + position.y), position.y + waveData.y * cos(waveData.x + position.x + position.y), position.z, 1);
	gl_Position = transformation * newPos;
	outColor = vec3(1,1,1);
}