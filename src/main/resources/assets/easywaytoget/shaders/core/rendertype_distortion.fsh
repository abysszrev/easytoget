#version 150

uniform sampler2D Sampler0;

in vec4 vertexColor;
in vec2 texCoord;
in vec2 lightCoord;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord);
    if (color.a < 0.1) {
        discard;
    }
    fragColor = color * vertexColor;
}