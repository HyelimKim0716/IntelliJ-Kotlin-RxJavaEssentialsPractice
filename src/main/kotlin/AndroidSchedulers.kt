class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())
            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (checkPermission())
                setEvent()
        } else {
            setEvent()
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun checkPermission() : Boolean {
        val permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)

        var checked = true
        permissions.forEach {
            if (ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_DENIED) {
                checked = false
            }
        }

        if (!checked) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE)
            return false
        }

        return true
    }

    private fun setEvent() {

    }

    private fun nonBlockingOperation() {
        val mDownloadProgress = PublishSubject.create<Int>()
        mDownloadProgress.distinct()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("mainThread / $it")
                    pb_wait_bitmap.progress = it
                }, {
                    it.stackTrace
                    println(it.message)
                }, {
                    println("Completed!")
                })

        val destination = "/sdcard/rxjavaessentials/test.avi"
        val destDir = File("/sdcard/rxjavaessentials/")
        if (!destDir.exists())
            destDir.mkdir()

        Observable.create<Boolean> {
//            val result =
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    setEvent()
            }
        }
    }
}
