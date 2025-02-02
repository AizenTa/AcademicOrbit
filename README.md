<!-- Header Section -->
<div align="left" style="background: linear-gradient(90deg, #1a73e8, #4285f4); color: #ffffff; padding: 20px; border-radius: 10px; position: relative;">
  <img src="https://raw.githubusercontent.com/Supreme-Zarck/AcademicOrbit/master/src/main/webapp/img/Logo.png" align="right" width="30%" style="margin: -20px 0 0 20px; border-radius: 10px;">
  <h1>ACADEMICORBIT 🚀</h1>
  <p align="left">
    <em>Academic Management System for Schools and Universities 🎓</em>
  </p>
  <p align="left">Built with the tools and technologies:</p>
  <p align="left">
    <a href="https://skillicons.dev">
      <img src="https://skillicons.dev/icons?i=java,mysql,html,css,tomcat&theme=light" alt="Tech Icons">
    </a>
  </p>
</div>
<br clear="right">


<!-- Table of Contents -->
## 📑 Table of Contents
- [Overview 🌟](#-overview)
- [Features ✨](#-features)
- [Project Structure 🗂️](#-project-structure)
  - [Project Index 📋](#-project-index)
- [Getting Started 🚀](#-getting-started)
  - [Prerequisites ✅](#-prerequisites)
  - [Installation 🛠️](#-installation)
  - [Usage ⚙️](#-usage)
- [Contributing 🤝](#-contributing)


<!-- Overview Section -->
## 🌟 Overview
AcademicOrbit is a comprehensive web application designed to streamline academic management for educational institutions. It provides three distinct portals for administrators, professors, and students with role-specific functionalities including academic record management, timetable scheduling, grade entry, and real-time statistics.

<!-- Features Section -->
## ✨ Features
### Admin Panel 🛡️
- **CRUD Operations**: Manage administrators, professors, students, classes, and modules 📝
- **Academic Statistics**: Generate reports on enrollment, gender distribution, and resource utilization 📊
- **Timetable Management**: Automatic scheduling with constraint-based optimization 📆
- **Resource Allocation**: Assign modules to professors and students to classes 🔄

### Professor Panel 👨‍🏫
- **Schedule Visualization**: View personalized teaching timetable 📅
- **Grade Management**: Enter and update student grades for assigned modules 🏆
- **Class Analytics**: Track student count and grading progress per module 📈

### Student Panel 🎒
- **Academic Overview**: Access personalized timetable and module details 🧑‍🎓


<!-- Project Structure Section -->
## 🗂️ Project Structure
```sh
└── AcademicOrbit/
    ├── src/main/java/          # Java source files
    │   ├── DAO/                # Data Access Objects
    │   ├── business/           # Entity classes
    │   └── controller/         # Servlet controllers
    ├── src/main/webapp/        # Frontend resources
    │   ├── admin/              # Admin interface JSPs
    │   ├── prof/               # Professor interface JSPs
    │   ├── etudiant/           # Student interface JSPs
    │   └── Style/              # CSS stylesheets
    └── gradebook.sql           # Database schema template
```


### 📋 Project Index
<details open>
	<summary><b><code>ACADEMICORBIT/</code></b></summary>
	<details>
		<summary><b>__root__</b> 📁</summary>
		<blockquote>
			<table>
			<tr>
				<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/gradebook.sql'>gradebook.sql</a></b></td>
				<td>MySQL database schema template with table definitions</td>
			</tr>
			</table>
		</blockquote>
	</details>
	<details>
		<summary><b>src</b> 📂</summary>
		<blockquote>
			<details>
				<summary><b>main</b> 📁</summary>
				<blockquote>
					<details>
						<summary><b>java</b> 💻</summary>
						<blockquote>
							<details>
								<summary><b>DAO</b> 🗃️</summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/DAO/ProfessorsDAO.java'>ProfessorsDAO.java</a></b></td>
										<td>Professor data access operations and CRUD implementations</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/DAO/GradebookDAO.java'>GradebookDAO.java</a></b></td>
										<td>Handles grade management and academic record operations</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/DAO/MaConnexion.java'>MaConnexion.java</a></b></td>
										<td>Database connection manager with connection pooling</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/DAO/AdminDAO.java'>AdminDAO.java</a></b></td>
										<td>Administrator data operations and system management</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/DAO/StudentDAO.java'>StudentDAO.java</a></b></td>
										<td>Student record management and class assignments</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>business</b> 🏷️</summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/business/Statistics.java'>Statistics.java</a></b></td>
										<td>Data model for academic statistics and analytics</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/business/Etudiant.java'>Etudiant.java</a></b></td>
										<td>Student entity class with enrollment attributes</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/business/Professors.java'>Professors.java</a></b></td>
										<td>Professor entity with module assignments</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/business/Personne.java'>Personne.java</a></b></td>
										<td>Base class for person entities (inheritance root)</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/business/Module.java'>Module.java</a></b></td>
										<td>Course module entity with scheduling details</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/business/Classe.java'>Classe.java</a></b></td>
										<td>Class entity representing student groups</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/business/Admin.java'>Admin.java</a></b></td>
										<td>Administrator entity with system privileges</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>controller</b> 🎛️</summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/controller/LoginController.java'>LoginController.java</a></b></td>
										<td>Handles user authentication and role-based routing</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/controller/AjouterEtudiant.java'>AjouterEtudiant.java</a></b></td>
										<td>Servlet for adding new student records</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/controller/ModifierClasse.java'>ModifierClasse.java</a></b></td>
										<td>Class information modification controller</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/controller/EnregistrerNotesServlet.java'>EnregistrerNotesServlet.java</a></b></td>
										<td>Grade entry and update processor</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/controller/SupprimerModule.java'>SupprimerModule.java</a></b></td>
										<td>Module deletion controller</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/controller/Logout.java'>Logout.java</a></b></td>
										<td>Session termination and logout handler</td>
									</tr>
									</table>
									<details>
										<summary><b>profservlets</b> 📜</summary>
										<blockquote>
											<table>
											<tr>
												<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/java/controller/profservlets/ProfEmplois.java'>ProfEmplois.java</a></b></td>
												<td>Professor timetable generator</td>
											</tr>
											</table>
										</blockquote>
									</details>
								</blockquote>
							</details>
						</blockquote>
					</details>
					<details>
						<summary><b>webapp</b> 🌐</summary>
						<blockquote>
							<details>
								<summary><b>admin</b> 🛠️</summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/webapp/admin/admin.jsp'>admin.jsp</a></b></td>
										<td>Admin dashboard interface</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/webapp/admin/add-prof.jsp'>add-prof.jsp</a></b></td>
										<td>Professor registration form</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/webapp/admin/module-liste.jsp'>module-liste.jsp</a></b></td>
										<td>Module management listing</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>prof</b> 👨‍💻</summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/webapp/prof/dashbordProf.jsp'>dashbordProf.jsp</a></b></td>
										<td>Professor dashboard with quick actions</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/webapp/prof/AffecterNotes.jsp'>AffecterNotes.jsp</a></b></td>
										<td>Grade assignment interface</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>etudiant</b> 🎓</summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/Supreme-Zarck/AcademicOrbit/blob/master/src/main/webapp/etudiant/dashbord.jsp'>dashbord.jsp</a></b></td>
										<td>Student dashboard with schedule</td>
									</tr>
									</table>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
		</blockquote>
	</details>
</details>


<!-- Getting Started Section -->
## 🚀 Getting Started
### ✅ Prerequisites
- **Java Development Kit**: JDK 8+ ☕
- **Database**: MySQL Server 5.7+ 🐬
- **Web Server**: Apache Tomcat 9+ 🔥
- **Dependencies**: 
  - MySQL Connector/J 8.0+ 🔌

### 🛠️ Installation
1. **Clone Repository**
```sh
git clone https://github.com/Supreme-Zarck/AcademicOrbit.git
cd AcademicOrbit
```
2. **Database Setup**
```sh
mysql -u root -p < gradebook.sql
```


# Build Dependencies

- **MySQL Connector/J:**  
  Add `mysql-connector-java-8.0.xx.jar` to the `WEB-INF/lib` directory.

- **Database Credentials:**  
  Configure the database credentials in the `MaConnexion.java` file.

- **Deployment:**  
  1. Deploy to Tomcat.
  2. Export the WAR file.
  3. Deploy the WAR file to the Tomcat `webapps` directory.



# ⚙️ Usage

## Access Application

- **URL:**  
  [http://localhost:8080/AcademicOrbit/Login.jsp](http://localhost:8080/AcademicOrbit/Login.jsp)

## Role-Specific Features

- **Admins:**  
  Access the admin dashboard at:  
  `/admin/admin.jsp`

- **Professors:**  
  Manage grades via:  
  `/prof/dashbordProf.jsp`

- **Students:**  
  View the schedule at:  
  `/etudiant/dashbord.jsp`


---
<!-- Contributing Section -->
## 🤝 Contributing
Contributions are welcome! Please follow these steps:
1. Fork the repository 🍴
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request 🚀

[![Contributors](https://contrib.rocks/image?repo=Supreme-Zarck/AcademicOrbit)](https://github.com/Supreme-Zarck/AcademicOrbit/graphs/contributors)
