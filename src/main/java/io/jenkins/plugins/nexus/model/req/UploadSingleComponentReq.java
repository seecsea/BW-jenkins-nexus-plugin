package io.jenkins.plugins.nexus.model.req;

import io.jenkins.plugins.nexus.utils.Utils;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class UploadSingleComponentReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private String group;
    private String artifactId;
    private String version;
    private boolean generatePom;
    private String packing;
    private List<FileAssert> fileAsserts;

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    public static class FileAssert implements Serializable {
        private static final long serialVersionUID = 1L;

        private File file;

        public FileAssert(File file) {
            this.file = file;
        }

        public String fileExt() {
            return Utils.getFileExt(file);
        }
    }

    public String toDirectory() {
        String groupPath = group.replace('.', '/');
        return Utils.splicePath(groupPath, artifactId, version);
    }
}