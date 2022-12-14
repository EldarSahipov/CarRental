USE [master]
GO
/****** Object:  Database [carrental]    Script Date: 03.10.2022 19:29:41 ******/
CREATE DATABASE [carrental]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'carrental', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\carrental.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'carrental_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\carrental_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [carrental] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [carrental].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [carrental] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [carrental] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [carrental] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [carrental] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [carrental] SET ARITHABORT OFF 
GO
ALTER DATABASE [carrental] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [carrental] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [carrental] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [carrental] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [carrental] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [carrental] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [carrental] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [carrental] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [carrental] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [carrental] SET  DISABLE_BROKER 
GO
ALTER DATABASE [carrental] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [carrental] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [carrental] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [carrental] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [carrental] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [carrental] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [carrental] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [carrental] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [carrental] SET  MULTI_USER 
GO
ALTER DATABASE [carrental] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [carrental] SET DB_CHAINING OFF 
GO
ALTER DATABASE [carrental] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [carrental] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [carrental] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [carrental] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [carrental] SET QUERY_STORE = OFF
GO
USE [carrental]
GO
USE [carrental]
GO
/****** Object:  Sequence [dbo].[hibernate_sequence]    Script Date: 03.10.2022 19:29:41 ******/
CREATE SEQUENCE [dbo].[hibernate_sequence] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
GO
/****** Object:  Table [dbo].[body_type]    Script Date: 03.10.2022 19:29:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[body_type](
	[id] [smallint] IDENTITY(1000,1) NOT NULL,
	[name] [varchar](128) NOT NULL,
 CONSTRAINT [PK_body_type] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[brand_car]    Script Date: 03.10.2022 19:29:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brand_car](
	[id] [int] IDENTITY(1000,1) NOT NULL,
	[name] [varchar](256) NOT NULL,
 CONSTRAINT [PK_brand_car] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[car]    Script Date: 03.10.2022 19:29:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[car](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[class_id] [smallint] NOT NULL,
	[brand_id] [int] NOT NULL,
	[number_car] [varchar](50) NOT NULL,
	[price] [int] NOT NULL,
	[city_id] [int] NOT NULL,
	[body_type_id] [smallint] NOT NULL,
	[transmission_id] [smallint] NOT NULL,
	[model] [varchar](128) NULL,
 CONSTRAINT [PK_car] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[city_car]    Script Date: 03.10.2022 19:29:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[city_car](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](256) NOT NULL,
 CONSTRAINT [PK_city_car] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[class_car]    Script Date: 03.10.2022 19:29:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[class_car](
	[id] [smallint] IDENTITY(1,1) NOT NULL,
	[name] [varchar](128) NOT NULL,
 CONSTRAINT [PK_class_car] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rental_car]    Script Date: 03.10.2022 19:29:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rental_car](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[tenant_phone] [varchar](128) NOT NULL,
	[end_lease] [date] NULL,
	[start_lease] [date] NULL,
	[car_id] [bigint] NOT NULL,
	[price] [int] NULL,
 CONSTRAINT [PK_rental_car] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tenant]    Script Date: 03.10.2022 19:29:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tenant](
	[first_name] [varchar](128) NULL,
	[last_name] [varchar](128) NULL,
	[phone_number] [varchar](128) NOT NULL,
	[year_driving] [smallint] NOT NULL,
 CONSTRAINT [PK_tenant] PRIMARY KEY CLUSTERED 
(
	[phone_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[transmission]    Script Date: 03.10.2022 19:29:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[transmission](
	[id] [smallint] IDENTITY(1,1) NOT NULL,
	[name] [varchar](128) NOT NULL,
 CONSTRAINT [PK_transmission] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 03.10.2022 19:29:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[password] [varchar](256) NOT NULL,
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[email] [varchar](128) NOT NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [users_email_uindex]    Script Date: 03.10.2022 19:29:41 ******/
CREATE UNIQUE NONCLUSTERED INDEX [users_email_uindex] ON [dbo].[users]
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[car] ADD  DEFAULT ('There is no model') FOR [model]
GO
ALTER TABLE [dbo].[car]  WITH CHECK ADD  CONSTRAINT [FK_car_body_type] FOREIGN KEY([body_type_id])
REFERENCES [dbo].[body_type] ([id])
GO
ALTER TABLE [dbo].[car] CHECK CONSTRAINT [FK_car_body_type]
GO
ALTER TABLE [dbo].[car]  WITH CHECK ADD  CONSTRAINT [FK_car_brand_car] FOREIGN KEY([brand_id])
REFERENCES [dbo].[brand_car] ([id])
GO
ALTER TABLE [dbo].[car] CHECK CONSTRAINT [FK_car_brand_car]
GO
ALTER TABLE [dbo].[car]  WITH CHECK ADD  CONSTRAINT [FK_car_city_car] FOREIGN KEY([city_id])
REFERENCES [dbo].[city_car] ([id])
GO
ALTER TABLE [dbo].[car] CHECK CONSTRAINT [FK_car_city_car]
GO
ALTER TABLE [dbo].[car]  WITH CHECK ADD  CONSTRAINT [FK_car_class_car] FOREIGN KEY([class_id])
REFERENCES [dbo].[class_car] ([id])
GO
ALTER TABLE [dbo].[car] CHECK CONSTRAINT [FK_car_class_car]
GO
ALTER TABLE [dbo].[car]  WITH CHECK ADD  CONSTRAINT [FK_car_transmission] FOREIGN KEY([transmission_id])
REFERENCES [dbo].[transmission] ([id])
GO
ALTER TABLE [dbo].[car] CHECK CONSTRAINT [FK_car_transmission]
GO
ALTER TABLE [dbo].[rental_car]  WITH CHECK ADD  CONSTRAINT [FK_rental_car_car] FOREIGN KEY([car_id])
REFERENCES [dbo].[car] ([id])
GO
ALTER TABLE [dbo].[rental_car] CHECK CONSTRAINT [FK_rental_car_car]
GO
ALTER TABLE [dbo].[rental_car]  WITH CHECK ADD  CONSTRAINT [FKf9lu74qndcx5n8l0cxgdrl7i4] FOREIGN KEY([tenant_phone])
REFERENCES [dbo].[tenant] ([phone_number])
GO
ALTER TABLE [dbo].[rental_car] CHECK CONSTRAINT [FKf9lu74qndcx5n8l0cxgdrl7i4]
GO
USE [master]
GO
ALTER DATABASE [carrental] SET  READ_WRITE 
GO
