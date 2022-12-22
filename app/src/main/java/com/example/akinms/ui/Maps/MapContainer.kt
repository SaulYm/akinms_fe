package com.example.akinms.ui.Maps



import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.akinms.domain.model.Bodega
import com.example.akinms.util.navigationGraph.BodegaScreen
import com.example.akinms.util.navigationGraph.Graph
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapContainer(
    navController: NavHostController,
    isLoading: Boolean = false,
    bodegas: List<Bodega> = emptyList()
){
    /*val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-12.056046487102194, -77.08448330743019), 17f)
    }*/
    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-12.056046487102194, -77.08448330743019), 18f)
    }

    LaunchedEffect(Unit) {
        multiplePermissionState.launchMultiplePermissionRequest()
    }
    /*Column() {
        for(bodega in bodegas){
            Text(text = bodega.nombre + " " + bodega.ubicacion.toString())
        }
    }*/
    println("HERE WE GOOOOO :" + bodegas.size)
    GoogleMap(
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true),
        //uiSettings = MapUiSettings(compassEnabled = true)
    ){
        println("HERE WE GOOOOO2")
        GoogleMarkers(bodegas = bodegas, navController = navController)
    }
}
@Composable
fun GoogleMarkers(bodegas:List<Bodega>, navController: NavHostController) {
    for(bodega in bodegas){
        Marker(
            state = rememberMarkerState(position = bodega.ubicacion),
            title = bodega.nombre,
            icon = BitmapDescriptorFactory.fromResource(com.example.akinms.R.drawable.tienda),
            onClick = {navController.navigate(Graph.BODEGA+"/"+bodega.id); true}
        )
    }
    /*
    Marker(
        state = rememberMarkerState(position = LatLng(44.811058, 20.4627586)),
        title = "Marker2",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
    )
    Marker(
        state = rememberMarkerState(position = LatLng(44.810058, 20.4627586)),
        title = "Marker3",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)
    )
    Marker(
        state = rememberMarkerState(position = LatLng(44.809058, 20.4627586)),
        title = "Marker4",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
    )
    Marker(
        state = rememberMarkerState(position = LatLng(44.809058, 20.4617586)),
        title = "Marker5",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)
    )*/
}