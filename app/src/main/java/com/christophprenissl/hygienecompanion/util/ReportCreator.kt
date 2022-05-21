package com.christophprenissl.hygienecompanion.util

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Environment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun createPdfFromReport(report: CoveringLetter, context: Context): String? {

    val pdfDocument = PdfDocument()

    val paint = Paint()

    val pageInfo = PdfDocument.PageInfo
        .Builder(PDF_WIDTH, PDF_HEIGHT, 1).create()
    val page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas

    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    paint.textSize = 15f
    paint.color = Color.Black.toArgb()

    canvas.drawText(report.description?: EMPTY, 209f, 100f, paint)
    canvas.drawText(report.date?.dayMonthYearString()?: EMPTY, 209f, 130f, paint)

    pdfDocument.finishPage(page)


    val fileName = report.let { it.description + "_" + it.seriesId + "_" + it.resultCreated?.dayMonthYearString() + ".pdf" }

    val storage = if(Build.VERSION.SDK_INT >= 30) Environment.getStorageDirectory() else context.getExternalFilesDir(null)
    val file = File(storage, fileName)

    return try {
        val fileStream = FileOutputStream(file)
        pdfDocument.writeTo(fileStream)
        storage?.path + fileName
    } catch (e: IOException) {
        e.printStackTrace()
        null
    } finally {
        pdfDocument.close()
    }
}
