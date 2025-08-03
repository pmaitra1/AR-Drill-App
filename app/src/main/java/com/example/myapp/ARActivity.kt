package com.example.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.Color
import com.google.ar.sceneform.rendering.MaterialFactory
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ShapeFactory
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import android.widget.Button
import android.widget.Toast

/**
 * ARActivity displays a cube in AR when the user taps a detected plane.
 * It also includes a Reset button to remove the cube and a Back button to exit the activity.
 */
class ARActivity : AppCompatActivity() {

    private var currentAnchorNode: AnchorNode? = null // Holds reference to the placed object
    private lateinit var arFragment: ArFragment
    private var placed = false // Optional: flag to restrict to one object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        // Initialize the AR fragment
        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment

        // Set listener to place cube when user taps on a detected plane
        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->

            // Remove existing object if one is already placed
            currentAnchorNode?.let {
                arFragment.arSceneView.scene.removeChild(it)
                it.anchor?.detach()
                currentAnchorNode = null
            }

            // Create a red opaque cube and place it at the tap location
            MaterialFactory.makeOpaqueWithColor(this, Color(android.graphics.Color.RED))
                .thenAccept { material ->
                    val cube = ShapeFactory.makeCube(
                        com.google.ar.sceneform.math.Vector3(0.1f, 0.1f, 0.1f), // Size
                        com.google.ar.sceneform.math.Vector3(0f, 0.05f, 0f),     // Center pivot
                        material
                    )

                    val anchor = hitResult.createAnchor()
                    val anchorNode = AnchorNode(anchor)
                    anchorNode.setParent(arFragment.arSceneView.scene)

                    val node = TransformableNode(arFragment.transformationSystem)
                    node.renderable = cube
                    node.setParent(anchorNode)
                    node.select()

                    currentAnchorNode = anchorNode
                }
        }

        // Reset button to remove the current cube from the scene
        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            currentAnchorNode?.let {
                arFragment.arSceneView.scene.removeChild(it)
                it.anchor?.detach()
                currentAnchorNode = null
                Toast.makeText(this, "Drill marker reset", Toast.LENGTH_SHORT).show()
            }
        }

        // Back button to return to the previous screen
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}

