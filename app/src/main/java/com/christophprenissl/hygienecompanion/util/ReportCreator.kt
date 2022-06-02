package com.christophprenissl.hygienecompanion.util

import android.content.Context
import android.os.Build
import android.os.Environment
import android.widget.Toast
import com.christophprenissl.hygienecompanion.model.entity.*
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.presentation.view.util.translation
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


fun createXSSFFromReport(
    context: Context,
    coveringLetterSeries: CoveringLetterSeries,
    report: CoveringLetter
) {
    if (coveringLetterSeries.id != report.seriesId) {
        Toast.makeText(context, ERROR_SAVING, Toast.LENGTH_SHORT).show()
        return
    }

    val workBook = XSSFWorkbook()
    val sheetName = report.description + "_" + report.date?.dayMonthYearString() + "_" + report.id
    val sheet = workBook.createSheet(sheetName)

    var rowIdx = 0
    val titleRow = sheet.createRow(rowIdx)
    titleRow.createKeyValue(idx = 0, key = COVERING_LETTER, coveringLetterSeries.description)
    rowIdx++

    val dateRow = sheet.createRow(rowIdx)
    dateRow.createKeyValue(0, SAMPLING_DATE, report.date?.dayMonthYearString())
    rowIdx++

    val resultToClientRow = sheet.createRow(rowIdx)
    resultToClientRow.createKeyValue(0, RESULT_TO_CLIENT, coveringLetterSeries.resultToClient)
    rowIdx++

    val resultToCoverageRow = sheet.createRow(rowIdx)
    resultToCoverageRow.createKeyValue(0, RESULT_TO_COVERING_ADDRESS, coveringLetterSeries.resultToTestingProperty)
    rowIdx++

    val costLocationRow = sheet.createRow(rowIdx)
    costLocationRow.createKeyValue(0, COST_LOCATION, coveringLetterSeries.costLocation)
    rowIdx += 2

    val samplerTitleRow = sheet.createRow(rowIdx)
    samplerTitleRow.createCell(0).setCellValue(SAMPLER)
    rowIdx++

    val samplerNameRow = sheet.createRow(rowIdx)
    samplerNameRow.createKeyValue(0, NAME, report.sampler?.name)
    rowIdx++

    val hasCertificateRow = sheet.createRow(rowIdx)
    hasCertificateRow.createKeyValue(0, HAS_CERTIFICATE, report.sampler?.hasCertificate)
    rowIdx++

    val isSamplerOfInstituteRow = sheet.createRow(rowIdx)
    isSamplerOfInstituteRow.createKeyValue(0, IS_SAMPLER_OF_INSTITUTE, report.sampler?.isSamplerOfInstitute)
    rowIdx += 2

    val labWorkerTitleRow = sheet.createRow(rowIdx)
    labWorkerTitleRow.createCell(0).setCellValue(LAB_WORKER)
    rowIdx++

    val labWorkerRow = sheet.createRow(rowIdx)
    labWorkerRow.createKeyValue(0, NAME, report.labWorker?.name)
    rowIdx += 2

    val labIdRow = sheet.createRow(rowIdx)
    labIdRow.createKeyValue(0, LAB_ID, coveringLetterSeries.laboratoryId)
    rowIdx += 2

    val normRow = sheet.createRow(rowIdx)
    normRow.createCell(0).setCellValue(NORM)
    coveringLetterSeries.bases?.forEachIndexed { idx, norm ->
        normRow.createCell(idx+1).setCellValue(norm.norm)
    }
    rowIdx++

    val addresses = listOf(
        coveringLetterSeries.client,
        coveringLetterSeries.sampleAddress,
        coveringLetterSeries.samplingCompany
    )
    rowIdx = sheet.createAddressesFields(rowIdx, addresses)
    rowIdx += 2

    val laboratoryInRow = sheet.createRow(rowIdx)
    laboratoryInRow.createKeyValue(0, LAB_IN_DATE, report.laboratoryIn?.dayMonthYearString())
    rowIdx++

    val resultCreatedRow = sheet.createRow(rowIdx)
    resultCreatedRow.createKeyValue(0, RESULT_CREATED_DATE, report.resultCreated?.dayMonthYearString())
    rowIdx += 2

    rowIdx = sheet.createParameterLists(
        rowIdx = rowIdx,
        title = BASIC_COVERING_PARAMETERS,
        parameterLists = listOf(report.basicCoveringParameters),
        parametersListTitles = listOf()
    )
    rowIdx++
    rowIdx = sheet.createParameterLists(
        rowIdx = rowIdx,
        title = BASIC_LAB_REPORT_PARAMETERS,
        parameterLists = listOf(report.basicLabReportParameters),
        parametersListTitles = listOf()
    )
    rowIdx += 2

    report.samples?.let {
        sheet.createSamplesLists(
        rowIdx = rowIdx,
        samples = it
        )
    }

    val fileName = "$sheetName.xlsx"
    val storage = if(Build.VERSION.SDK_INT >= 30) Environment.getStorageDirectory() else context.getExternalFilesDir(null)
    val file = File(storage, fileName)

    try {
        val fileStream = FileOutputStream(file)
        workBook.write(fileStream)
        val path = storage?.path + fileName
        Toast.makeText(context, String.format(SUCCESS_SAVING, path), Toast.LENGTH_SHORT).show()
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, ERROR_SAVING, Toast.LENGTH_SHORT).show()
    }
}

private fun XSSFRow.createKeyValue(idx: Int, key: String, value: String?) {
    createCell(idx).setCellValue(key)
    createCell(idx+1).setCellValue(value)
}

private fun XSSFRow.createKeyValue(idx: Int, key: String, value: Boolean?) {
    createCell(idx).setCellValue(key)
    val valueTranslated = value?.translation()
    createCell(idx+1).setCellValue(valueTranslated)
}

private fun XSSFSheet.createAddressesFields(rowIdx: Int, addresses: List<Address?>): Int {
    var newRowIdx = rowIdx
    val titleRow = createRow(rowIdx)
    titleRow.createCell(0).setCellValue(ADDRESS)
    titleRow.createCell(1).setCellValue(CLIENT_ADDRESS)
    titleRow.createCell(2).setCellValue(COVERING_ADDRESS)
    titleRow.createCell(3).setCellValue(COVERING_COMPANY_ADDRESS)
    newRowIdx++
    val nameRow = createRow(newRowIdx)
    nameRow.createCell(0).setCellValue(ADDRESS_NAME)
    newRowIdx++
    val zipRow = createRow(newRowIdx)
    zipRow.createCell(0).setCellValue(ZIP)
    newRowIdx++
    val cityRow = createRow(newRowIdx)
    cityRow.createCell(0).setCellValue(CITY_NAME)
    newRowIdx++
    val contactNameRow = createRow(newRowIdx)
    contactNameRow.createCell(0).setCellValue(CONTACT_NAME)
    newRowIdx++
    val streetRow = createRow(newRowIdx)
    streetRow.createCell(0).setCellValue(STREET)
    newRowIdx++
    val phoneRow = createRow(newRowIdx)
    phoneRow.createCell(0).setCellValue(PHONE)
    newRowIdx++
    val faxRow = createRow(newRowIdx)
    faxRow.createCell(0).setCellValue(FAX)
    newRowIdx++
    val eMailRow = createRow(newRowIdx)
    eMailRow.createCell(0).setCellValue(EMAIL)

    addresses.forEachIndexed { idx, address ->
        address?.let {
            nameRow.createCell(idx+1).setCellValue(address.name)
            zipRow.createCell(idx+1).setCellValue(address.zip)
            cityRow.createCell(idx+1).setCellValue(address.city)
            contactNameRow.createCell(idx+1).setCellValue(address.contactName)
            streetRow.createCell(idx+1).setCellValue(address.street)
            phoneRow.createCell(idx+1).setCellValue(address.phone)
            faxRow.createCell(idx+1).setCellValue(address.fax)
            eMailRow.createCell(idx+1).setCellValue(address.eMail)
        }
    }
    return newRowIdx
}

private fun XSSFSheet.createParameterLists(
    rowIdx: Int,
    title: String = "",
    parameterLists: List<List<Parameter>?>,
    parametersListTitles: List<String>
): Int {
    var newRowIdx = rowIdx
    val titleRow = createRow(rowIdx)
    titleRow.createCell(0).setCellValue(title)
    parametersListTitles.forEachIndexed { idx, pTitle ->
        titleRow.createCell(idx+1).setCellValue(pTitle)
    }
    newRowIdx++

    val rows = mutableListOf<XSSFRow>()
    parameterLists.forEachIndexed { idx1, parameterList ->
        parameterList?.forEachIndexed { idx2, parameter ->
            if (idx2 >= rows.size) {
                val row = createRow(newRowIdx)
                newRowIdx++
                rows.add(row)
                row.createCell(0).setCellValue(parameter.name)
            }
            val valueTranslated = if (parameter.parameterType == ParameterType.Bool)
                    (parameter.value as? Boolean)?.translation() else parameter.value.toString()
            rows[idx2].createCell(idx1+1).setCellValue(valueTranslated)
        }
    }
    return newRowIdx
}

private fun XSSFSheet.createSamplesLists(
    rowIdx: Int,
    samples: List<Sample>
): Int {
    var newRowIdx = rowIdx
    val sampleTitles = samples.map { it.id?: EMPTY }
    val samplingParameters = mutableListOf<List<Parameter>>()
    val labParameters = mutableListOf<List<Parameter>>()

    samples.forEach { sample ->
        sample.coveringSampleParameters?.let { samplingParameters.add(it) }
        sample.labSampleParameters?.let { labParameters.add(it) }
    }
    val titleRow = createRow(newRowIdx)
    titleRow.createCell(0).setCellValue(SAMPLES)
    newRowIdx++

    newRowIdx = createParameterLists(
        rowIdx = newRowIdx,
        title = COVERING_SAMPLE_PARAMETERS,
        parameterLists = samplingParameters,
        parametersListTitles = sampleTitles
    )
    newRowIdx++

    newRowIdx = createParameterLists(
        rowIdx = newRowIdx,
        title = LAB_SAMPLE_PARAMETERS,
        parameterLists = labParameters,
        parametersListTitles = sampleTitles
    )
    newRowIdx++

    val sampleLocationRow = createRow(newRowIdx)
    sampleLocationRow.createCell(0).setCellValue(SAMPLE_LOCATION)
    newRowIdx++
    val datesRow = createRow(newRowIdx)
    datesRow.createCell(0).setCellValue(SAMPLING_DATE)
    newRowIdx++
    val extraInfoSamplingRow = createRow(newRowIdx)
    extraInfoSamplingRow.createCell(0).setCellValue(EXTRA_INFO_SAMPLING_PERSON)
    newRowIdx++
    val extraInfoLabRow = createRow(newRowIdx)
    extraInfoLabRow.createCell(0).setCellValue(EXTRA_INFO_LAB_PERSON)
    newRowIdx++
    val warningRow = createRow(newRowIdx)
    warningRow.createCell(0).setCellValue(WARNING)
    samples.forEachIndexed { idx, sample ->
        sampleLocationRow.createCell(idx+1).setCellValue(sample.sampleLocation?.description)
        datesRow.createCell(idx+1).setCellValue(sample.created?.dayMonthYearString())
        extraInfoSamplingRow.createCell(idx+1).setCellValue(sample.extraInfoSampling)
        extraInfoLabRow.createCell(idx+1).setCellValue(sample.extraInfoLaboratory)
        warningRow.createCell(idx+1).setCellValue(sample.warningMessage)
    }
    return newRowIdx
}

