#version 150

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in ivec2 UV2;

uniform sampler2D Sampler2D;
uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform float GameTime; // Minecraft 提供的时间变量

out vec4 vertexColor;
out vec2 texCoord;
out vec2 lightCoord;

void main() {
    // 基于时间和顶点位置计算扭曲偏移量
    float distortionX = sin(Position.y * 0.5 + GameTime * 0.1) * 0.05;
    float distortionY = cos(Position.x * 0.5 + GameTime * 0.1) * 0.05;

    vec3 distortedPos = Position + vec3(distortionX, distortionY, 0.0);

    gl_Position = ProjMat * ModelViewMat * vec4(distortedPos, 1.0);

    vertexColor = Color;
    texCoord = UV0;
    lightCoord = texture(Sampler2D, vec2(UV2) / 256.0).rg;
}