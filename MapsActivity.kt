package com.example.googlemaps

import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.googlemaps.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val LATLNG = LatLng(37.566418, 126.977943)//서울시청의 위도와 경도 좌푯값

        var bitmapDrawable: BitmapDrawable
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){//아이콘 저장하기
            bitmapDrawable = getDrawable(R.drawable.marker) as BitmapDrawable
        }else{
            bitmapDrawable = resources.getDrawable(R.drawable.marker) as BitmapDrawable
        }
        var discriptor = BitmapDescriptorFactory.fromBitmap(bitmapDrawable.bitmap)


        val cameraPosition = CameraPosition.Builder()//카메라 포지션 설정
            .target(LATLNG)
            .zoom(15.0f)
            .build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)//카메라 포지션에 지도에서 사용할 수 있는 카메라 정보 생성
        mMap.moveCamera(cameraUpdate)//카메라 포지션 기준으로 지도의 위치, 배율, 기울기 등이 변경되어 표시됨.

        val markerOptions = MarkerOptions()//마커 추가
            .position(LATLNG)//마커 좌표
            //.title("Seoul City Hall")//마커 제목
            //.snippet("37.566418, 126.977943")//정보창
            .icon(discriptor)//마커 아이콘 설정

        mMap.addMarker(markerOptions)//구글 지도에 마커 추가




    }
}