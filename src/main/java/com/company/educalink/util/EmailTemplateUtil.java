package com.company.educalink.util;

import com.company.educalink.entity.interfaces.Nameable;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Utility class for handling email template loading and placeholder formatting.
 * <p>
 * Provides helper methods to load HTML templates, replace placeholders with actual values,
 * and build default placeholder maps for entities implementing the {@link Nameable} interface.
 * </p>
 *
 * This class cannot be instantiated.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
public class EmailTemplateUtil {

    /**
     * Private constructor to prevent instantiation.
     */
    private EmailTemplateUtil() {

    }

    /**
     * Loads an email template from the classpath and returns its content as a string.
     *
     * @param path the classpath location of the template
     * @return the content of the template as a {@code String}
     * @throws UncheckedIOException if the template cannot be read
     */
    public static String loadTemplate(String path) {
        try (InputStream inputStream = new ClassPathResource(path).getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo leer el template de correo", e);
        }
    }

    /**
     * Replaces placeholders in the given template with values from the provided map.
     * <p>
     * Each placeholder should be wrapped in curly braces, e.g., {@code {Name}}.
     *
     * @param template the template string containing placeholders
     * @param values a map of key-value pairs to replace placeholders in the template
     * @return the formatted template with all placeholders replaced
     */
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

    /**
     * Builds a map of default placeholders for the given entity.
     * <p>
     * Currently, it builds a single key-value pair: {@code "Name"} with the full name.
     *
     * @param <T> a type that implements {@link Nameable}
     * @param entity the entity to extract values from
     * @return a map containing placeholder values
     */
    public static <T extends Nameable> Map<String, String> buildPlaceholders(T entity) {
        return Map.of("Name", entity.getName() + " " + entity.getLastName());
    }
}
