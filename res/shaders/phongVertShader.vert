#version 130

in vec3 position;
in vec2 texCoordIn;
in vec3 normalIn;
in vec3 tangentIn;

out vec3 normal_out;
out vec3 tangent_out;
out vec3 vertexPosition;
out vec3 outColor;
out vec2 texCoord; 
out float isWater_out;
out mat4 modelMatrix_out;

uniform float isWater;

uniform float waveDataX;
uniform float waveDataY;


uniform mat4 transformation;
uniform mat4 modelMatrix;

void main(){
	outColor = vec3(1.0, 1.0, 1.0);
	modelMatrix_out = modelMatrix;
	isWater_out = isWater;
	texCoord = texCoordIn;
	
	vertexPosition = (modelMatrix * vec4(position, 1.0)).xyz;
	normal_out = mat3(modelMatrix) * normalIn;
	tangent_out = mat3(modelMatrix) * tangentIn;
	
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