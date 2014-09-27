#version 130

in vec2 texCoord;
in vec3 viewSpaceNormal;
in vec3 viewSpaceTangent;
in vec3 viewSpacePos;

in float isWater_out;
in mat4 modelview;

out vec4 fragColor;

uniform sampler2D sampler;
uniform sampler2D normalMap;

uniform float time;

uniform vec3 viewSpaceLightPos;

uniform float material_shininess;
uniform vec3 material_diffuse_color; 
uniform vec3 material_specular_color; 
uniform vec3 material_emissive_color; 

uniform vec3 scene_ambient_light;
uniform vec3 scene_light = vec3(0.9, 0.9, 0.9);

vec3 calcBumpedNormal(){
	vec3 normal = normalize(viewSpaceNormal);
	vec3 tangent = normalize(viewSpaceTangent);
	
	tangent = normalize(tangent - dot(tangent, normal) * normal);
	
	vec3 biTangent = cross(tangent, normal);
	vec3 bumpMapNormal = (texture2D(normalMap, texCoord)).xyz;
	
	bumpMapNormal = 2.0 * bumpMapNormal - vec3(1.0, 1.0, 1.0);
	
	vec3 newNormal;
	
	mat3 TBN = mat3(tangent, biTangent, normal);
	
	newNormal = TBN * bumpMapNormal;
	newNormal = normalize(newNormal);
	
	return newNormal;
}

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

vec4 calculateFresnel(vec3 materialSpecular, vec3 normal, vec3 directionFromEye)
{
	return vec4(materialSpecular, 1.0) + vec4((vec3(1.0) - materialSpecular), 1.0) * pow(clamp(1.0 + dot(directionFromEye, normal), 0.0, 1.0), 5.0);
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

void main() 
{
	//vec3 viewSpaceLightPos = vec3(600, 500, -100);
	//vec3 viewSpacePos = vec3(-100, 100, 0);
	
	
	
	
	vec3 normal = calcBumpedNormal();
	
	vec3 directionToLight = normalize(viewSpaceLightPos - viewSpacePos);
	directionToLight = normalize(rotateY(directionToLight, time));
	vec3 directionFromEye = normalize(viewSpacePos);
	vec3 reflectionVec = (modelview * vec4(reflect(directionFromEye, normal), 0.0)).xyz;
	
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
	
	vec3 specular = material_specular_color;
	vec4 diffuse = texColor * vec4(material_diffuse_color,1.0);
	vec4 ambient = texColor * vec4(material_diffuse_color,1.0);
	vec4 emissive = texColor * vec4(material_emissive_color, 1.0);
	
	vec4 ambientTotal = calculateAmbient(scene_ambient_light, ambient);
	vec4 diffuseTotal = calculateDiffuse(scene_light, diffuse, normal, directionToLight);
	vec4 specularTotal = calculateSpecular(scene_light, specular, material_shininess, normal, directionToLight, directionFromEye);
	//vec4 fresnel = calculateFresnel(specular, normal, directionFromEye);
	
	vec4 shading = ambientTotal + diffuseTotal + specularTotal + emissive;
	
	fragColor = shading;
}