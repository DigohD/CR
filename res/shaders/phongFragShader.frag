#version 130


in vec2 texCoord; 
in vec3 normal_out;
in vec3 vertexPosition;

out vec4 fragColor;

in float isWater_out;

uniform sampler2D sampler;

uniform float time;
uniform float k;

uniform vec3 lightPosition;
uniform vec3 lightPosition2;
uniform vec3 eyePosition;

uniform float material_shininess;
uniform vec3 material_diffuse_color; 
uniform vec3 material_specular_color; 
uniform vec3 material_emissive_color; 

uniform vec3 scene_ambient_light;
uniform vec3 scene_light = vec3(0.6, 0.6, 0.6);


vec4 calcAmbientLight(vec3 sceneAmbientLight, vec4 materialAmbient){
	return vec4(sceneAmbientLight, 1.0) * materialAmbient;
}

vec4 calcDiffuseLight(vec3 sceneLight, vec4 materialDiffuse, vec3 directionToLight, vec3 normal){
	return vec4(sceneLight, 1.0) * materialDiffuse * dot(directionToLight, normal);
}

vec4 calcSpecularLight(vec3 sceneLight, vec3 directionFromEye, vec3 reflectedLight){
	float normalizationFactor = (material_shininess + 2.0) / 8.0;
	float specular = pow(dot(reflectedLight, directionFromEye), material_shininess) ;
	return vec4(sceneLight, 1.0) * vec4(material_specular_color, 1.0) * specular;	
}

vec4 calcSpecularLight2(vec3 specularLight, float materialShininess, vec3 normal, vec3 directionToLight, vec3 directionFromEye){
	vec3 h = normalize(directionToLight - directionFromEye);
	float normalizationFactor = ((materialShininess + 2.0) / 8.0);
	return vec4(specularLight, 1.0) * vec4(material_specular_color, 1.0) * pow(max(0, dot(h, normal)), materialShininess) * normalizationFactor;
}

vec3 rotateY(vec3 v, float angle){
	vec3 x = vec3(cos(angle), 0, -1.0 * sin(angle));
	vec3 y = vec3(0, 1.0, 0);
	vec3 z = vec3(sin(angle), 0, cos(angle));
	
	mat3 RM = mat3(x, y, z);
	vec3 result = v * RM;
	return result;
}

vec3 rotateX(vec3 v, float angle){
	vec3 x = vec3(1.0, 0, 0);
	vec3 y = vec3(0, cos(angle), sin(angle));
	vec3 z = vec3(0, -1.0*sin(angle), cos(angle));
	
	mat3 RM = mat3(x, y, z);
	vec3 result = v * RM;
	return result;
}

vec3 rotateZ(vec3 v, float angle){
	vec3 x = vec3(cos(angle), 0, sin(angle));
	vec3 y = vec3(-1.0*sin(angle), 0, cos(angle));
	vec3 z = vec3(0,0,1.0);
	
	mat3 RM = mat3(x, y, z);
	vec3 result = v * RM;
	return result;
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
	vec3 directionToLight2 = normalize(lightPosition2 - vertexPosition);
	//directionToLight = rotateZ(directionToLight, time);
	vec4 diffuse = texColor * vec4(material_diffuse_color, 1.0);
	vec4 diffuseLight = calcDiffuseLight(scene_light, diffuse, directionToLight, normal); 
	vec4 diffuseLight2 = 8.7f * calcDiffuseLight(scene_light, diffuse, directionToLight2, normal); 

	//specular light
	vec3 directionFromEye = normalize(lightPosition - vertexPosition);
	vec3 directionFromEye2 = normalize(lightPosition2 - vertexPosition);
	//directionFromEye2 = rotateZ(directionFromEye2, 3.14/2);
	vec3 reflectedLight = reflect(-directionToLight, normal);
	vec3 reflectedLight2 = reflect(-directionToLight2, normal);
	//vec4 specLight = calcSpecularLight2(scene_light, material_shininess, normal, directionToLight, directionFromEye);
	vec4 specularLight = calcSpecularLight(scene_light, directionFromEye, reflectedLight);
	vec4 specularLight2 = calcSpecularLight(scene_light, directionFromEye2, reflectedLight2);
	
	//emissive light calculations
	vec4 emissive = texColor * vec4(material_emissive_color, 1.0);
	
	vec4 shading;
	
	if(isWater_out == 1){
		
		
		if(time >= 3.14 && time <= 3.14*2.0){
			shading = ambientLight + clamp(diffuseLight, 0, 1) + emissive;
		}else{
			shading = ambientLight + clamp(diffuseLight, 0, 1) + (k*clamp(diffuseLight2, 0, 1)) + (k*clamp(specularLight2, 0, 1)) + emissive;
		}
		
	}else{
		shading = ambientLight + clamp(diffuseLight, 0, 1)  + emissive;
	}
	
	
	if(texture2D(sampler, texCoord.xy).w == 0){
		shading = vec4(0);
	}
	
	fragColor = shading;
}