# OPEN-MOTION

This Java application allows users to interact with a webcam, capturing images and adjusting settings such as resolution. It provides a graphical user interface (GUI) for easy navigation and control.
Please keep in mind this is a work-in-progress. It was made to deal with the lack of affordable stop-motion software. Eventually I would like to port it with C++ and to integrate the Canon SDK to support DSLRs.
## Features

- **Webcam Integration**: Utilizes the `Sarxos-Webcam` library to access and interact with the webcam connected to the system.
- **Resolution Selection**: Allows users to select the desired resolution for capturing images from a dropdown menu.
- **Live Preview**: Displays a live preview of the webcam feed in a resizable panel.
- **Image Capture**: Enables users to capture images by pressing the space bar, saving them to a specified directory on the system.

## Getting Started

1. **Prerequisites**:
   - Ensure you have Java installed on your system.

2. **Clone the Repository**:
   - Clone or download the repository to your local machine.

3. **Install Dependencies**:
   - Make sure to include the `Webcam` library in your project dependencies.

4. **Run the Application**:
   - Compile and run the `mainClass.java` file to launch the application.

## Usage

1. **Webcam Selection**:
   - Upon launching the application, it automatically detects the connected webcam. If multiple webcams are available, select the desired webcam from the dropdown menu.

2. **Resolution Selection**:
   - Choose the resolution for capturing images from the dropdown menu. The available resolutions are displayed for selection.

3. **Live Preview**:
   - A live preview of the webcam feed is displayed in the main panel of the application. This preview updates in real-time to reflect changes in camera settings or positioning.

4. **Image Capture**:
   - Press the space bar to capture an image from the webcam feed. The captured image is saved to a specified directory on the system.

## Work in Progress

- **Onion-skinning**: Implementing onion-skinning functionality to overlay previous frames on the live preview for animation reference.
- **Scene Loader**: Developing a scene loader feature to import and display pre-recorded video files for playback and analysis.
- **Thumbnails**: Adding thumbnail generation capability to create small preview images for captured images and videos.
- **Project Explorer**: Introducing a project explorer tool to organize and manage saved images, videos, and project files within the application.
- **Canon SDK Integration**: Implementing Canon camera functionality in order to control camera settings and use DSLRs as webcams.

## Contributing

Contributions to the project are welcome! Feel free to fork the repository, make changes, and submit pull requests to contribute improvements, new features, or bug fixes.

## License

This project is licensed under the [MIT License](LICENSE).
