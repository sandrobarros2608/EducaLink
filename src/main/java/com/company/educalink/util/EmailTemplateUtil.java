package com.company.educalink.util;

import com.company.educalink.entity.interfaces.Nameable;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class EmailTemplateUtil {

    private EmailTemplateUtil() {

    }

    public static String loadTemplate(String path) {
        try (InputStream inputStream = new ClassPathResource(path).getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo leer el template de correo", e);
        }
    }

    public static String formatRegisterName(String template, Map<String, String> values) {
        if (template == null || values == null) {
            return template;
        }

        for (Map.Entry<String, String> entry : values.entrySet()) {
            String placeholder = "{" + entry.getKey() + "}";
            template = template.replace(placeholder, entry.getValue());
        }
        return template;
    }

    public static <T extends Nameable> Map<String, String> buildPlaceholders(T entity) {
        return Map.of("Name", entity.getName() + " " + entity.getLastName());
    }
}
