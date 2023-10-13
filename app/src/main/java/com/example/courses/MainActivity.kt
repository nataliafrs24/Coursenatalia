
package com.example.courses

import android.os.Bundle  // Mengimpor pustaka Bundle untuk mengelola data saat Activity dibuat.

import androidx.activity.ComponentActivity  // Mengimpor kelas ComponentActivity yang digunakan untuk mengelola aktivitas.

import androidx.activity.compose.setContent  // Mengimpor fungsi setContent yang digunakan untuk menentukan tampilan aktivitas dengan Compose.

import androidx.compose.foundation.Image  // Mengimpor komponen Image dari Compose Foundation, digunakan untuk menampilkan gambar.

import androidx.compose.foundation.layout.Arrangement  // Mengimpor Arrangement dari Compose Foundation, digunakan untuk mengatur tata letak komponen.

import androidx.compose.foundation.layout.Box  // Mengimpor komponen Box dari Compose Foundation, digunakan untuk menampung komponen lain.

import androidx.compose.foundation.layout.Column  // Mengimpor komponen Column dari Compose Foundation, digunakan untuk tata letak vertikal.

import androidx.compose.foundation.layout.Row  // Mengimpor komponen Row dari Compose Foundation, digunakan untuk tata letak horizontal.

import androidx.compose.foundation.layout.aspectRatio  // Mengimpor komponen aspectRatio dari Compose Foundation, digunakan untuk mengatur rasio aspek.

import androidx.compose.foundation.layout.fillMaxSize  // Mengimpor komponen fillMaxSize dari Compose Foundation, digunakan untuk mengisi ruang penuh.

import androidx.compose.foundation.layout.padding  // Mengimpor komponen padding dari Compose Foundation, digunakan untuk menambahkan jarak.

import androidx.compose.foundation.layout.size  // Mengimpor komponen size dari Compose Foundation, digunakan untuk mengatur ukuran.

import androidx.compose.foundation.lazy.grid.GridCells  // Mengimpor GridCells dari Compose Foundation, digunakan untuk mengatur sel-sel dalam grid.

import androidx.compose.foundation.lazy.grid.LazyVerticalGrid  // Mengimpor LazyVerticalGrid dari Compose Foundation, digunakan untuk menampilkan daftar dalam tata letak grid vertikal.

import androidx.compose.foundation.lazy.grid.items  // Mengimpor komponen items dari Compose Foundation, digunakan untuk mengiterasi melalui daftar item.

import androidx.compose.material3.Card  // Mengimpor komponen Card dari Compose Material3, digunakan untuk membuat kartu.

import androidx.compose.material3.Icon  // Mengimpor komponen Icon dari Compose Material3, digunakan untuk menampilkan ikon.

import androidx.compose.material3.MaterialTheme  // Mengimpor MaterialTheme dari Compose Material3, digunakan untuk menerapkan tema Material Design.

import androidx.compose.material3.Surface  // Mengimpor komponen Surface dari Compose Material3, digunakan untuk menentukan latar belakang dan isi aktivitas.

import androidx.compose.material3.Text  // Mengimpor komponen Text dari Compose Material3, digunakan untuk menampilkan teks.

import androidx.compose.runtime.Composable  // Mengimpor anotasi Composable yang digunakan untuk mendefinisikan komponen Compose.

import androidx.compose.ui.Alignment  // Mengimpor Alignment dari Compose UI, digunakan untuk mengatur tata letak komponen.

import androidx.compose.ui.Modifier  // Mengimpor Modifier dari Compose UI, digunakan untuk mengatur modifikasi komponen.

import androidx.compose.ui.layout.ContentScale  // Mengimpor ContentScale dari Compose UI, digunakan untuk mengatur skala konten gambar.

import androidx.compose.ui.res.dimensionResource  // Mengimpor dimensionResource dari Compose UI, digunakan untuk mengambil dimensi dari sumber daya.

import androidx.compose.ui.res.painterResource  // Mengimpor painterResource dari Compose UI, digunakan untuk mengambil gambar dari sumber daya.

import androidx.compose.ui.res.stringResource  // Mengimpor stringResource dari Compose UI, digunakan untuk mengambil string dari sumber daya.

import androidx.compose.ui.tooling.preview.Preview  // Mengimpor anotasi Preview yang digunakan untuk pratinjau tampilan.

import androidx.compose.ui.unit.dp  // Mengimpor unit dp dari Compose UI, digunakan untuk mengukur dalam satuan Density-independent Pixels.

import com.example.courses.data.DataSource  // Mengimpor DataSource dari paket aplikasi (kemungkinan adalah data palsu atau simulasi).

import com.example.courses.model.Topic  // Mengimpor model Topic dari paket aplikasi, mungkin merepresentasikan topik kursus.

import com.example.courses.ui.theme.CoursesTheme  // Mengimpor tema aplikasi dari paket aplikasi.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // Menginisialisasi tampilan Activity dengan tema aplikasi CoursesTheme.
                // Surface adalah kontainer utama yang menentukan latar belakang aplikasi.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil komponen TopicGrid untuk menampilkan daftar topik.
                    TopicGrid(
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),  // Menampilkan topik dalam grid dengan 2 kolom.
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        // Iterasi melalui daftar topik dari sumber daya DataSource.
        items(DataSource.topics) { topic ->
            // Menampilkan setiap topik dalam kartu dengan komponen TopicCard.
            TopicCard(topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    // Menggunakan komponen Card untuk membuat kartu topik.
    Card {
        Row {
            Box {
                Image(
                    painter = painterResource(id = topic.imageRes),
                    contentDescription = null,
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }

            Column {
                Text(
                    text = stringResource(id = topic.name),  // Menampilkan nama topik.
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                    Text(
                        text = topic.availableCourses.toString(),  // Menampilkan jumlah kursus yang tersedia.
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    CoursesTheme {
        // Membuat pratinjau topik dengan komponen TopicCard.
        val topic = Topic(R.string.photography, 321, R.drawable.photography)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopicCard(topic = topic)
        }
    }
}

