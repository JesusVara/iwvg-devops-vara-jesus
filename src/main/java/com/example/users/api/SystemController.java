package com.example.users.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class SystemController {

    private static final String BADGE_IMAGE_TEMPLATE = """
            <svg xmlns="http://www.w3.org/2000/svg" width="%d" height="20">
                <linearGradient id="a" x2="0" y2="100%%">
                    <stop offset="0" stop-color="#bbb" stop-opacity=".1"/>
                    <stop offset="1" stop-opacity=".1"/>
                </linearGradient>
                <rect rx="3" width="%d" height="20" fill="#555"/>
                <rect rx="3" x="%d" width="%d" height="20" fill="#4c1"/>
                <path fill="#4c1" d="M%d 0h4v20h-4z"/>
                <rect rx="3" width="%d" height="20" fill="url(#a)"/>
                <g fill="#fff" text-anchor="middle" font-family="DejaVu Sans,Verdana,Geneva,sans-serif" font-size="11">
                    <text x="%d" y="15" fill="#010101" fill-opacity=".3">%s</text>
                    <text x="%d" y="14">%s</text>
                    <text x="%d" y="15" fill="#010101" fill-opacity=".3">%s</text>
                    <text x="%d" y="14">%s</text>
                </g>
            </svg>
            """;
    private static final int TEXT_MARGIN = 12;
    private static final int CHARACTER_WIDTH = 6;

    @Value("${info.app.artifact:users-service}")
    private String artifact;

    @Value("${info.app.version:unknown}")
    private String version;

    @Value("${info.app.build:unknown}")
    private String build;

    @GetMapping({"/", "/system"})
    public String applicationInfo() {
        return "{\"version\":\"" + artifact + "::" + version + "::" + build + "\"}";
    }

    @GetMapping(value = {"/version-badge", "/system/version-badge"}, produces = "image/svg+xml")
    public byte[] generateBadge() {
        return generateBadge("Render", "v" + version).getBytes(StandardCharsets.UTF_8);
    }

    private String generateBadge(String label, String value) {
        int widthLabel = TEXT_MARGIN + CHARACTER_WIDTH * label.length();
        int widthValue = TEXT_MARGIN + CHARACTER_WIDTH * value.length();
        int textWidth = widthLabel + widthValue;
        int middleLabel = widthLabel / 2;
        int middleValue = widthLabel + widthValue / 2;
        return String.format(BADGE_IMAGE_TEMPLATE, textWidth, textWidth, widthLabel, widthValue, widthLabel,
                textWidth, middleLabel, label, middleLabel, label, middleValue, value, middleValue, value);
    }
}
