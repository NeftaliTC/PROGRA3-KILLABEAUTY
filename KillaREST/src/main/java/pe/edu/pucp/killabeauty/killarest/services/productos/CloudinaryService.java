package pe.edu.pucp.killabeauty.killarest.services.productos;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.util.Map;

public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "TU_CLOUD_NAME",
                "api_key", "TU_API_KEY",
                "api_secret", "TU_API_SECRET"
        ));
    }

    public String subirImagen(File archivo) throws Exception {
        Map resultado = cloudinary.uploader().upload(
                archivo,
                ObjectUtils.asMap("folder", "killa_beauty/productos")
        );

        return resultado.get("secure_url").toString();
    }
}
