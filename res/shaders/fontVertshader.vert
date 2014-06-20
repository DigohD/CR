#version 130

in vec3 position;
in vec2 texCoordIn;


out vec2 texCoord; 
out vec3 outColor;

uniform mat4 transformation;
uniform float red;
uniform float green;
uniform float blue;

void main() 
{
	gl_Position = transformation * vec4(position, 1);
	texCoord = texCoordIn;
	
	outColor = vec3(1,1,1);
}