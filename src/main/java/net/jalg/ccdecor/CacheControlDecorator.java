package net.jalg.ccdecor;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
@Cacheable
public class CacheControlDecorator implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		for (Annotation a : responseContext.getEntityAnnotations()) {
			if (a.annotationType() == Cacheable.class) {
				String cc = ((Cacheable) a).cc();
				responseContext.getHeaders().add("Cache-Control", cc);
			}
		}
	}
}
