package com.example.mytrainingsession

import androidx.lifecycle.ViewModel

class ExercisesViewModel(): ViewModel() {
    val baseDuration = 5
    private val listOfExercises = listOf(
        Exercise(
            "Dumbbell",
            "This dumbbell exercise targets the upper body, specifically the shoulders and arms. Hold a dumbbell in each hand, press them overhead, and lower back down, maintaining a controlled motion for muscle engagement.",
            baseDuration * 2,
            R.drawable.gauntlets
        ),
        Exercise(
            "Bicycle",
            "To ride a bike, sit on the saddle, place your feet on the pedals, and push off the ground. Pedal forward while balancing, steer with the handlebars, and brake gently to stop.",
            baseDuration,
            R.drawable.bicycle
        ),
        Exercise(
            "Football",
            "To play soccer, kick the ball to pass or shoot, dribble to maintain control, and use teamwork to defend and attack. Stay aware of the field, follow rules, and aim for the goal.",
            baseDuration,
            R.drawable.football
        ),
        Exercise(
            "Jumping",
            "To jump, bend your knees slightly, swing your arms back, and then push off the ground with your legs. Keep your body upright, and land softly to avoid injury.",
            baseDuration,
            R.drawable.jumping
        ),
        Exercise(
            "Jumping with rope",
            "To jump rope, hold the handles, swing the rope over your head, and jump as it passes under your feet. Keep a steady rhythm, land softly on your toes, and maintain good posture.",
            baseDuration,
            R.drawable.jumpingwithjumper
        ),
        Exercise(
            "Squat with dumbbells",
            "To squat with dumbbells, hold a dumbbell in each hand at your sides. Stand with feet shoulder-width apart, bend your knees, lower your hips, and return to standing, keeping your back straight.",
            baseDuration,
            R.drawable.legs
        ),
        Exercise(
            "Raise legs",
            "To raise your legs while lying on your back, keep your arms at your sides, engage your core, and slowly lift your legs toward the ceiling. Lower them back down without touching the floor.",
            baseDuration,
            R.drawable.legsonspine
        ),
        Exercise(
            "Wheel",
            "To perform a wheel exercise, kneel on the floor, grip the wheel handles, and roll it forward while keeping your body straight. Engage your core, then return to the starting position",
            baseDuration * 2,
            R.drawable.ring
        ),
        Exercise(
            "Rollers",
            "To run on rollers, start by standing tall with feet shoulder-width apart. Push off gently, maintaining balance. Keep your knees slightly bent, and use your arms for stability while rolling forward.",
            baseDuration * 2,
            R.drawable.rollers
        ),
        Exercise(
            "Running",
            "To run, stand tall with your feet shoulder-width apart. Start by jogging slowly, then gradually increase speed. Keep your posture upright, engage your core, and use your arms to maintain balance.",
            baseDuration * 2,
            R.drawable.running
        )
    )

    fun getListOfExercises(): List<Exercise> {
        return listOfExercises
    }

    fun getExercise(index: Int): Exercise {
        return listOfExercises[index]
    }

    fun isOutOfBounds(index: Int): Boolean {
        return index !in listOfExercises.indices
    }
}