package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.common.logger
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

/**
 * Created by irotsoma on 7/8/2016.
 * Unzip utility
 * based on java example from Oracle website:  http://www.oracle.com/technetwork/articles/java/compress-1565076.html
 */
class Unzip {
    companion object { val LOG by logger() }
    @Throws(IOException::class)
    fun unZipAllToFolder(zipFile: File, outputFolder: File)  {

        val buffer = ByteArray(1024)
        //create output directory is not exists
        if (!outputFolder.exists()) {
            outputFolder.mkdir()
        }
        //get the zip file content
        val zis = ZipInputStream(FileInputStream(zipFile))
        //get the zipped file list entry
        var ze: ZipEntry? = zis.nextEntry
        //cycle through all of the files
        while (ze != null) {

            val fileName = ze.name
            val newFile = File(outputFolder, fileName)

            LOG.debug("file unzip : " + newFile.absoluteFile)

            //create all non-existing folders
            File(newFile.parent).mkdirs()

            val fos = FileOutputStream(newFile)

            var len: Int = zis.read(buffer)
            while (len > 0) {
                fos.write(buffer, 0, len)
                len = zis.read(buffer)
            }
            fos.close()
            ze = zis.nextEntry
        }
        zis.closeEntry()
        zis.close()
    }
}