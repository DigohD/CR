#version 130

in vec3 position;
in vec2 texCoordIn;

out vec2 texCoord; 
out vec3 outColor;

uniform float waveDataX;
uniform float waveDataY;
uniform mat4 transformation;

void main() 
{
	outColor = vec3(1,1,1);
	texCoord = texCoordIn;
	
	vec4 a_position = vec4(position, 1);
	vec4 newPos = vec4(a_position.x + waveDataY * sin(waveDataX+a_position.x+a_position.y), a_position.y + waveDataY * cos(waveDataX+a_position.x+a_position.y), a_position.z, a_position.w);
	
	gl_Position = transformation * newPos;
}