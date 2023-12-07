package com.comp304_group4_lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comp304_group4_lab6.ui.theme.Comp304_group4_lab6Theme


data class Program(val name: String, val courses: List<Course>)

data class Course(val name: String, val description: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Comp304_group4_lab6Theme {
                val programs = createPrograms()
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppContent(programs)
                }
            }
        }
    }

    private fun createPrograms(): List<Program> {
        return listOf(
            Program(
                getString(R.string.P1), listOf(
                    Course(getString(R.string.P1C1), getString(R.string.P1C1D)),
                    Course(getString(R.string.P1C2), getString(R.string.P1C2D)),
                    Course(getString(R.string.P1C3), getString(R.string.P1C3D))
                )
            ),
            Program(
                getString(R.string.P2), listOf(
                    Course(getString(R.string.P2C1), getString(R.string.P2C1D)),
                    Course(getString(R.string.P2C2), getString(R.string.P2C2D)),
                    Course(getString(R.string.P2C3), getString(R.string.P2C3D))
                )
            ),
            Program(
                getString(R.string.P3), listOf(
                    Course(getString(R.string.P3C1), getString(R.string.P3C1D)),
                    Course(getString(R.string.P3C2), getString(R.string.P3C2D)),
                    Course(getString(R.string.P3C3), getString(R.string.P3C3D))
                )
            ),
            Program(
                getString(R.string.P4), listOf(
                    Course(getString(R.string.P4C1), getString(R.string.P4C1D)),
                    Course(getString(R.string.P4C2), getString(R.string.P4C2D)),
                    Course(getString(R.string.P4C3), getString(R.string.P4C3D))
                )
            )
        )
    }
}

@Composable
fun AppContent(programs: List<Program>) {
    var selectedProgram by remember { mutableStateOf<Program?>(null) }
    var selectedCourse by remember { mutableStateOf<Course?>(null) }

    if (selectedProgram == null) {
        ProgramList(programs) { program ->
            selectedProgram = program
        }
    } else if (selectedCourse == null) {
        selectedProgram?.let { program ->
            CourseList(program.courses) { course ->
                selectedCourse = course
            }
        }
    } else {
        // Display the course description based on the selected course
        selectedCourse?.let { course ->
            CourseDescription(course)
        }
    }
}

@Composable
fun ProgramList(programs: List<Program>, onItemClick: (Program) -> Unit) {
    LazyColumn {
        items(programs) { program ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onItemClick(program) }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = program.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tap to view courses",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun CourseList(courses: List<Course>, onCourseClick: (Course) -> Unit) {
    LazyColumn {
        items(courses) { course ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onCourseClick(course) }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = course.name,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tap for course details",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun CourseDescription(course: Course) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = course.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = course.description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    val programs = listOf(Program(
        "Jetpack Compose", listOf(
            Course("Compose Course 1", "Description for Compose Course 1"),
            Course("Compose Course 2", "Description for Compose Course 2"),
            Course("Compose Course 3", "Description for Compose Course 3")
        )))
    Comp304_group4_lab6Theme {
        AppContent(programs)
    }
}
