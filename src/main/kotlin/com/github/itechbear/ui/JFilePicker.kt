package com.github.itechbear.ui

import java.awt.FlowLayout
import java.awt.event.ActionEvent
import javax.swing.*

/**
 * Custom Swing component combining label + text field + browse button for file selection.
 */
class JFilePicker(
    private val textFieldLabel: String,
    private val buttonLabel: String
) : JPanel(FlowLayout(FlowLayout.CENTER, 5, 5)) {

    private val label: JLabel = JLabel(textFieldLabel)
    val textField: JTextField = JTextField(30)
    private val button: JButton = JButton(buttonLabel)
    private val fileChooser: JFileChooser = JFileChooser()

    var mode: Int = MODE_OPEN

    init {
        button.addActionListener { event -> buttonActionPerformed(event) }

        add(label)
        add(textField)
        add(button)
    }

    private fun buttonActionPerformed(event: ActionEvent) {
        when (mode) {
            MODE_OPEN -> {
                if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    textField.text = fileChooser.selectedFile.absolutePath
                }
            }
            MODE_SAVE -> {
                if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    textField.text = fileChooser.selectedFile.absolutePath
                }
            }
        }
    }

    val selectedFilePath: String
        get() = textField.text

    companion object {
        const val MODE_OPEN = 1
        const val MODE_SAVE = 2
    }
}
