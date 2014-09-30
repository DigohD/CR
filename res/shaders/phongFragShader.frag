#version 130


in vec2 texCoord; 
in vec3 normal_out;
in vec3 vertexPosition;

out vec4 fragColor;

in float isWater_out;

uniform sampler2D sampler;

uniform vec3 lightPosition;
uniform vec3 eyePosition;

uniform float material_shininess;
uniform vec3 material_diffuse_color; 
uniform vec3 material_specular_color; 
uniform vec3 material_emissive_color; 

uniform vec3 scene_ambient_light;
uniform vec3 scene_light = vec3(2.9, 2.9, 2.9);


vec4 calcAmbientLight(vec3 sceneAmbientLight, vec4 materialAmbient){
	return vec4(sceneAmbientLight, 1.0) * materialAmbient;
}

vec4 calcDiffuseLight(vec3 sceneLight, vec4 materialDiffuse, vec3 directionToLight, vec3 normal){
	return vec4(sceneLight, 1.0) * materialDiffuse * dot(directionToLight, normal);
}

vec4 calcSpecularLight(vec3 sceneLight, vec3 directionFromEye, vec3 reflectedLight){
	float normalizationFactor = (material_shininess + 2.0) / 8.0;
	float specular = pow(dot(reflectedLight, directionFromEye), material_shininess) * normalizationFactor;
	return vec4(sceneLight, 1.0) * vec4(material_specular_color, 1.0) * specular;	
}

void main(){
	vec3 normal = normalize(normal_out);
	
	vec4 texColor;
	
	if(isWater_out == 1){
		vec2 a2DVectorTemp = vec2(0, 0.26f);
		vec2 a2DVectorTemp2 = vec2(-0.5f, 0);
		vec2 a2DVector = texCoord + a2DVectorTemp;
		vec2 a2DVector2 = texCoord + a2DVectorTemp2;
	
		texColor = (texture2D(sampler, texCoord.xy) * 0.3f) + (texture2D(sampler, a2DVector.xy) * 0.2f) + (texture2D(sampler, a2DVector2.xy) * 0.8f);
	}else{
		texColor = texture2D(sampler, texCoord.xy);
	}
	
	//ambient light
	vec4 ambient = texColor * vec4(material_diffuse_color, 1.0);
	vec4 ambientLight = calcAmbientLight(scene_ambient_light, ambient); 
	
	//diffuse light
	vec3 directionToLight = normalize(lightPosition - vertexPosition);
	vec4 diffuse = texColor * vec4(material_diffuse_color, 1.0);
	vec4 diffuseLight = calcDiffuseLight(scene_light, diffuse, directionToLight, normal); 

	//specular light
	vec3 directionFromEye = normalize(eyePosition - vertexPosition);
	vec3 reflectedLight = normalize(reflect(lightPosition, normal));
	vec4 specularLight = calcSpecularLight(scene_light, directionFromEye, reflectedLight);
	
	//emissive light calculations
	vec4 emissive = texColor * vec4(material_emissive_color, 1.0);
	
	vec4 shading = ambientLight + clamp(diffuseLight, 0, 1.0) + clamp(specularLight, 0, 1.0) + emissive;
	
	if(texture2D(sampler, texCoord.xy).w == 0){
		shading = vec4(0);
	}
	
	fragColor = shading;
}