#version 130

in vec2 texCoord;
in vec3 viewSpaceNormal;
in vec3 viewSpacePos;
out vec4 fragColor;

uniform float material_shininess = 65.0;
uniform vec3 material_diffuse_color = vec3(2.6); 
uniform vec3 material_specular_color = vec3(3); 
uniform vec3 material_emissive_color = vec3(1.6); 

uniform sampler2D sampler;
//uniform vec3 viewSpaceLightPos;
uniform vec3 scene_ambient_light = vec3(0.1, 0.1, 0.1);
uniform vec3 scene_light = vec3(1.6, 1.6, 1.6);

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

void main() {
	vec3 viewSpaceLightPos = vec3(-1, 1, -1);
	vec3 vsp = -vec3(-1, 1, 1);
	vec3 normal = normalize(viewSpaceNormal);
	vec3 directionToLight = normalize(viewSpaceLightPos - vsp);
	vec3 directionFromEye = normalize(vsp);
	
	vec3 specular = material_specular_color;
	vec4 diffuse = texture2D(sampler, texCoord.xy) * vec4(material_diffuse_color,1.0);
	vec4 ambient = texture2D(sampler, texCoord.xy) * vec4(material_diffuse_color,1.0);
	vec4 emissive = texture2D(sampler, texCoord.xy) * vec4(material_emissive_color, 1.0);
	
	vec4 ambientTotal = calculateAmbient(scene_ambient_light, ambient );
	vec4 diffuseTotal = calculateDiffuse(scene_light, diffuse, normal, directionToLight);
	vec4 specularTotal = calculateSpecular(scene_light, specular, material_shininess, normal, directionToLight, directionFromEye);
	
	vec4 shading = ambientTotal + diffuseTotal + specularTotal + emissive;
								 
	fragColor = shading;
}