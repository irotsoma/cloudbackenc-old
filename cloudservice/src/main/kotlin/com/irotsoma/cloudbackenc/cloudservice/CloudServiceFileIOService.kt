package com.irotsoma.cloudbackenc.cloudservice

/**
 * Created by irotsoma on 6/20/2016.
 */
interface CloudServiceFileIOService {
    fun upload(filePath: File) : Boolean
    fun list(dirPath: File) : List<File>
    fun download(filePath: File) : InputStream
}