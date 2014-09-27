#version 130

in vec3 position;
in vec2 texCoordIn;

out vec2 texCoord; 
out vec3 outColor;

uniform mat4 transformation;

void main() 
{
	texCoord = texCoordIn;
	outColor = vec3(1,1,1f);
	gl_Position = transformation * vec4(position, 1);
}