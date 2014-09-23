#version 130

in vec3 position;
in vec2 texCoordIn;
in vec3 normal;

out vec2 texCoord; 
out vec3 outColor;

uniform mat4 transformation;
uniform float time;

void main() 
{
	gl_Position = transformation * vec4(position, 1);
	texCoord = texCoordIn;
	
	outColor = vec3(1,1,1) * time;
}