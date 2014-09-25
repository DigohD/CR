#version 130

in vec3 position;
in vec2 texCoordIn;
in vec3 normalIn;

out vec2 texCoord; 
out vec3 normalOut;

out vec3 viewSpaceNormal;
out vec3 viewSpacePos;

uniform mat4 transformation;
uniform mat4 normalMatrix;
uniform mat4 modelViewMatrix;

uniform float time;


void main() 
{

	gl_Position = transformation * vec4(position, 1);
   	viewSpaceNormal = (modelViewMatrix * vec4(normalIn, 0.0)).xyz;
   	viewSpacePos = (modelViewMatrix * vec4(position, 1.0)).xyz;
	texCoord = texCoordIn;
	
}