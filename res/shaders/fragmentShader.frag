#version 130

in vec2 texCoord;
in vec3 viewSpaceNormal;
in float isWater_out;

out vec4 fragColor;

uniform sampler2D sampler;
uniform sampler2D sampler2;

uniform float material_shininess;
uniform vec3 material_diffuse_color; 
uniform vec3 material_specular_color; 
uniform vec3 material_emissive_color; 

uniform vec3 scene_ambient_light = vec3(0.2, 0.2, 0.2);
uniform vec3 scene_light = vec3(2, 2, 2);

vec4 calculateAmbient(vec3 ambientLight, vec4 materialAmbient){
	return (vec4(ambientLight,1.0) * materialAmbient);
}

vec4 calculateDiffuse(vec3 diffuseLight, vec4 materialDiffuse, vec3 normal, vec3 directionToLight){
	return vec4(diffuseLight, 1.0) * materialDiffuse * max(0, dot(normal, directionToLight));
}

vec4 calculateSpecular(vec3 specularLight, vec3 materialSpecular, float materialShininess, vec3 normal, vec3 directionToLight, vec3 directionFromEye){
	vec3 h = normalize(directionToLight - directionFromEye);
	float normalizationFactor = ((materialShininess + 2.0) / 8.0);
	return vec4(specularLight, 1.0) * vec4(materialSpecular, 1.0) * pow(max(0, dot(h, normal)), materialShininess) * normalizationFactor;
}

void main() 
{
	vec3 viewSpaceLightPos = vec3(-100, 100, -100);
	vec3 viewSpacePos = vec3(-100, 10, 0);
	vec3 normal = normalize(viewSpaceNormal);
	vec3 directionToLight = normalize(viewSpaceLightPos - viewSpacePos);
	vec3 directionFromEye = normalize(viewSpacePos);
	
	vec4 texColor;
	
	if(isWater_out == 1){
		vec2 a2DVectorTemp = vec2(0, 0.26f);
		vec2 a2DVectorTemp2 = vec2(-0.5f, 0);
		vec2 a2DVector = texCoord + a2DVectorTemp;
		vec2 a2DVector2 = texCoord + a2DVectorTemp2;
	
		texColor = (texture2D(sampler, texCoord.xy) * 0.3f) + (texture2D(sampler2, a2DVector.xy) * 0.2f) + (texture2D(sampler2, a2DVector2.xy) * 0.8f);
	}else{
		texColor = texture2D(sampler, texCoord.xy);
	}
	
	vec3 specular = material_specular_color;
	vec4 diffuse = texColor * vec4(material_diffuse_color,1.0);
	vec4 ambient = texColor * vec4(material_diffuse_color,1.0);
	vec4 emissive = texColor * vec4(material_emissive_color, 1.0);
	
	vec4 ambientTotal = calculateAmbient(scene_ambient_light, ambient);
	vec4 diffuseTotal = calculateDiffuse(scene_light, diffuse, normal, directionToLight);
	vec4 specularTotal = calculateSpecular(scene_light, specular, material_shininess, normal, directionToLight, directionFromEye);
	
	vec4 shading = ambientTotal + diffuseTotal + specularTotal + emissive;

	fragColor = shading;
}