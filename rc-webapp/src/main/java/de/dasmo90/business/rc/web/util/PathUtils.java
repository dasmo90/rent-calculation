package de.dasmo90.business.rc.web.util;

public final class PathUtils {

	public static String path(String... pathParts) {
		StringBuilder sb = new StringBuilder();
		for(String pathPart : pathParts) {
			sb.append(pathPart);
		}
		return sb.toString();
	}
}
