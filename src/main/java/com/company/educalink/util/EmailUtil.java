package com.company.educalink.util;

import com.company.educalink.entity.Student;
import com.company.educalink.entity.Teacher;
import com.company.educalink.exception.custom.InvalidPlaceholderEntityException;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Utility class for handling email template loading and placeholder formatting.
 * <p>
 * Provides helper methods to load HTML templates, replace placeholders with actual values
 * </p>
 *
 * This class cannot be instantiated.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
public class EmailUtil {

    /**
     * Private constructor to prevent instantiation.
     */
    private EmailUtil() {

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
    public static String formatTemplate(String template, Map<String, String> values) {
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
     * Builds a map of placeholders for email templates based on the provided entity type.
     * <p>
     * This method inspects the given entity and creates a map of key-value pairs
     * where each key represents a placeholder name (e.g., {@code {Name}}) and the value
     * corresponds to the entity's attributes. Currently supports {@link Student} and
     * {@link Teacher} entities.
     * </p>
     *
     * @param entity the entity from which to build placeholders (must be a {@code Student} or {@code Teacher})
     * @return a map of placeholders and their corresponding values
     * @throws InvalidPlaceholderEntityException if the provided entity type is not supported
     */
    public static Map<String, String> registrationPlaceholdersBuild(Object entity) {
        if (entity instanceof Student student) {
            return Map.of("Name", student.getName() + " " + student.getLastName());
        } else if (entity instanceof Teacher teacher) {
            return Map.of("Name", teacher.getName() + " " + teacher.getLastName());
        } else {
            throw new InvalidPlaceholderEntityException(entity.getClass());
        }
    }

    public static Map<String, String> assignmentPlaceholdersBuild(Object entity) {
        throw new UnsupportedOperationException("Method not implement.");
    }
}
