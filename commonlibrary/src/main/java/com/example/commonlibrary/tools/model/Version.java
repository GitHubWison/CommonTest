package com.example.commonlibrary.tools.model;

/**
 * Created by Nangy-Office on 2016/4/11.
 */
public class Version {

        private String versionCode;
        private String fileName;
        private String loadURL;

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getLoadURL() {
            return loadURL;
        }

        public void setLoadURL(String loadURL) {
            this.loadURL = loadURL;
        }

        @Override
        public String toString() {
            return super.toString();
        }

}
