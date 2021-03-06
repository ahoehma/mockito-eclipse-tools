package pl.greenpath.mockito.ide.refactoring;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.ui.ISharedImages;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

public final class PluginImages {

    private PluginImages() {
        imageRegistry.put(ISharedImages.IMG_FIELD_PRIVATE, DESC_FIELD_PRIVATE);
        imageRegistry.put(ISharedImages.IMG_OBJS_LOCAL_VARIABLE, DESC_LOCAL_VARIABLE);
    }

    private static final IPath ICONS_PATH = new Path("/icons");
    private static final String NAME_PREFIX = "org.eclipse.jdt.ui.";
    private static final String T_OBJ = "obj16";
    public static final ImageDescriptor DESC_FIELD_PRIVATE = createManagedFromKey(T_OBJ,
            ISharedImages.IMG_FIELD_PRIVATE);
    public static final ImageDescriptor DESC_LOCAL_VARIABLE = createManagedFromKey(T_OBJ,
            ISharedImages.IMG_OBJS_LOCAL_VARIABLE);
    private static final PluginImages INSTANCE = new PluginImages();

    private final ImageRegistry imageRegistry = new ImageRegistry();

    public static PluginImages getInstance() {
        return INSTANCE;
    }

    public ImageRegistry getImageRegistry() {
        return imageRegistry;
    }

    public Image get(final String description) {
        return imageRegistry.get(description);
    }

    private static ImageDescriptor createManagedFromKey(final String prefix, final String key) {
        return createManaged(prefix, key.substring(NAME_PREFIX.length()));
    }

    private static ImageDescriptor createManaged(final String prefix, final String name) {
        final ImageDescriptor result = create(prefix, name);
        return result;
    }

    private static ImageDescriptor create(final String prefix, final String name) {
        final IPath path = ICONS_PATH.append(prefix).append(name);
        return createImageDescriptor(Activator.getDefault().getBundle(), path);
    }

    public static ImageDescriptor createImageDescriptor(final Bundle bundle, final IPath path) {
        final URL url = FileLocator.find(bundle, path, null);
        return ImageDescriptor.createFromURL(url);
    }
}
