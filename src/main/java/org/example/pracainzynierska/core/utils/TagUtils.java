package org.example.pracainzynierska.core.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagUtils {
    public static List<String> splitTags(String tags) {
        return Arrays.stream(tags.split(","))
                .map(String::trim)
                .filter(tag -> !tag.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
